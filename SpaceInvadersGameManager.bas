B4A=true
Group=MultiGame
ModulesStructureVersion=1
Type=Class
Version=13.4
@EndOfDesignText@
'========================================
' SPACE INVADERS GAME MANAGER CLASS
' Manages Space Invaders game state and logic
'========================================
Sub Class_Globals
	Private gameCanvas As Canvas
	Private gamePanel As Panel
	Private soundManager As SoundManager
	
	Public IsActive As Boolean
	Public IsPausedState As Boolean
	Private currentScore As Int
	Private currentWave As Int
	Private highScore As Int
	Private lives As Int
	
	' Player with smooth animation
	Private playerX As Float
	Private playerTargetX As Float
	Private playerY As Int
	Private playerWidth As Int = 40
	Private playerHeight As Int = 30
	Private Const PLAYER_SMOOTHNESS As Float = 0.25
	
	' Invaders
	Private invaders As List
	Private invaderDirection As Int = 1
	Private invaderSpeed As Int = 4
	
	' NEW: Boss System
	Private isBossWave As Boolean
	Private bossActive As Boolean
	Private bossX As Float
	Private bossY As Int
	Private bossWidth As Int = 100
	Private bossHeight As Int = 70
	Private bossHealth As Int
	Private bossMaxHealth As Int = 30
	Private bossPattern As Int = 0
	
	' NEW: Combo System
	Private comboCount As Int
	Private comboTimer As Int
	Private comboMultiplier As Float = 1.0
	Private Const COMBO_TIMEOUT As Int = 90
	
	' NEW: Power-ups
	Private powerups As List
	Private activePowerup As String = "NONE"
	Private powerupTimer As Int = 0
	Private Const POWERUP_DURATION As Int = 200
	
	' NEW: Particles for explosions
	Private particles As List
	
	Private playerBullets As List
	Private enemyBullets As List
	Private shields As List
	
	Private frameCounter As Int
	Private invaderMoveInterval As Int = 15
	
	' Colors
	Private Const COLOR_BACKGROUND As Int = 0xFF282828
	Private Const COLOR_PLAYER As Int = 0xFF00ff00
	Private Const COLOR_INVADER As Int = 0xFFff00ff
	Private Const COLOR_BOSS As Int = 0xFFff3333
	Private Const COLOR_POWERUP As Int = 0xFFffaa00
	Private Const SCORE_INVADER As Int = 10
	Private Const SCORE_BOSS As Int = 500
	Private Const INITIAL_LIVES As Int = 3
End Sub

Public Sub Initialize(panel As Panel)
	gamePanel = panel
	gameCanvas.Initialize(gamePanel)
	soundManager.Initialize
	soundManager.LoadAudio
	
	playerBullets.Initialize
	enemyBullets.Initialize
	invaders.Initialize
	shields.Initialize
	powerups.Initialize
	particles.Initialize
	
	highScore = 0
	ResetGameState
End Sub

Public Sub StartNewGame
	ResetGameState
	IsActive = True
	IsPausedState = False
	soundManager.StartMusic
End Sub

Private Sub ResetGameState
	currentScore = 0
	currentWave = 1
	lives = INITIAL_LIVES
	frameCounter = 0
	comboCount = 0
	comboTimer = 0
	comboMultiplier = 1.0
	
	playerX = gamePanel.Width / 2 - playerWidth / 2
	playerTargetX = playerX
	playerY = gamePanel.Height - 60
	
	InitializeWave
	playerBullets.Clear
	enemyBullets.Clear
	powerups.Clear
	particles.Clear
End Sub

Private Sub InitializeWave
	invaders.Clear
	shields.Clear
	
	' Every 5th wave is a boss wave
	isBossWave = (currentWave Mod 5 = 0)
	
	If isBossWave Then
		bossActive = True
		bossX = gamePanel.Width / 2 - bossWidth / 2
		bossY = 80
		bossHealth = bossMaxHealth + (currentWave * 3)
		bossMaxHealth = bossHealth
		bossPattern = 0
	Else
		' Regular invaders
		Dim rows As Int = 5
		Dim cols As Int = 11
		For row = 0 To rows - 1
			For col = 0 To cols - 1
				Dim inv As Map
				inv.Initialize
				inv.Put("x", 50 + (col * 40))
				inv.Put("y", 80 + (row * 35))
				inv.Put("alive", True)
				invaders.Add(inv)
			Next
		Next
	End If
	
	' Create shields
	For i = 1 To 4
		Dim shield As Map
		shield.Initialize
		shield.Put("x", i * (gamePanel.Width / 5) - 30)
		shield.Put("y", gamePanel.Height - 150)
		shield.Put("health", 10)
		shields.Add(shield)
	Next
End Sub

Public Sub Pause
	IsPausedState = True
	soundManager.PauseMusic
End Sub

Public Sub Resume
	IsPausedState = False
	soundManager.ResumeMusic
End Sub

'========================================
' UPDATE LOOP
'========================================
Public Sub Update
	If Not(IsActive) Or IsPausedState Then Return
	
	frameCounter = frameCounter + 1
	
	' Smooth player movement
	If playerX < playerTargetX Then
		playerX = playerX + (playerTargetX - playerX) * PLAYER_SMOOTHNESS
		If Abs(playerTargetX - playerX) < 1 Then playerX = playerTargetX
	Else If playerX > playerTargetX Then
		playerX = playerX - (playerX - playerTargetX) * PLAYER_SMOOTHNESS
		If Abs(playerX - playerTargetX) < 1 Then playerX = playerTargetX
	End If
	
	' Update combo timer
	If comboTimer > 0 Then
		comboTimer = comboTimer - 1
		If comboTimer = 0 Then
			comboCount = 0
			comboMultiplier = 1.0
		End If
	End If
	
	' Update powerup timer
	If powerupTimer > 0 Then
		powerupTimer = powerupTimer - 1
		If powerupTimer = 0 Then
			activePowerup = "NONE"
		End If
	End If
	
	' Update game objects
	If isBossWave And bossActive Then
		UpdateBoss
	Else
		If frameCounter Mod invaderMoveInterval = 0 Then
			UpdateInvaders
		End If
	End If
	
	UpdateBullets
	UpdatePowerups
	UpdateParticles
	CheckCollisions
	CheckWinCondition
End Sub

Private Sub UpdateBoss
	' Boss movement
	bossX = bossX + 2 * Sin(frameCounter * 0.05)
	
	' Boss fire patterns
	If frameCounter Mod 50 = 0 Then
		bossPattern = (bossPattern + 1) Mod 3
		Select bossPattern
			Case 0  ' Single shot
				FireBossBullet(bossX + bossWidth/2, bossY + bossHeight, 0, 0)
			Case 1  ' Triple shot
				For i = -1 To 1
					FireBossBullet(bossX + bossWidth/2 + (i*20), bossY + bossHeight, i*2, 0)
				Next
			Case 2  ' Spread
				For i = -2 To 2
					FireBossBullet(bossX + bossWidth/2, bossY + bossHeight, i*3, i)
				Next
		End Select
	End If
End Sub

Private Sub FireBossBullet(x As Float, y As Int, vx As Float, offsetX As Int)
	Dim bullet As Map
	bullet.Initialize
	bullet.Put("x", x + offsetX)
	bullet.Put("y", y)
	If vx <> 0 Then bullet.Put("vx", vx)
	enemyBullets.Add(bullet)
End Sub

Private Sub UpdateInvaders
	Dim leftmost As Int = gamePanel.Width
	Dim rightmost As Int = 0
	Dim shouldDrop As Boolean = False
	
	For Each inv As Map In invaders
		If inv.Get("alive") Then
			Dim x As Int = inv.Get("x")
			If x < leftmost Then leftmost = x
			If x > rightmost Then rightmost = x
		End If
	Next
	
	If invaderDirection = 1 And rightmost >= gamePanel.Width - 40 Then
		invaderDirection = -1
		shouldDrop = True
	Else If invaderDirection = -1 And leftmost <= 10 Then
		invaderDirection = 1
		shouldDrop = True
	End If
	
	For Each inv As Map In invaders
		If inv.Get("alive") Then
			Dim x As Int = inv.Get("x")
			Dim y As Int = inv.Get("y")
			If shouldDrop Then
				inv.Put("y", y + 20)
			Else
				inv.Put("x", x + invaderDirection * invaderSpeed)
			End If
		End If
	Next
	
	' Random enemy fire
	If Rnd(0, 20) = 0 Then
		Dim alive As List
		alive.Initialize
		For Each inv As Map In invaders
			If inv.Get("alive") Then alive.Add(inv)
		Next
		If alive.Size > 0 Then
			Dim shooter As Map = alive.Get(Rnd(0, alive.Size))
			Dim bullet As Map
			bullet.Initialize
			bullet.Put("x", shooter.Get("x") + 15)
			bullet.Put("y", shooter.Get("y") + 25)
			enemyBullets.Add(bullet)
		End If
	End If
End Sub

Private Sub UpdateBullets
	For i = playerBullets.Size - 1 To 0 Step -1
		Dim b As Map = playerBullets.Get(i)
		Dim y As Int = b.Get("y") - 15
		If y < 0 Then
			playerBullets.RemoveAt(i)
		Else
			b.Put("y", y)
		End If
	Next
	
	For i = enemyBullets.Size - 1 To 0 Step -1
		Dim b As Map = enemyBullets.Get(i)
		Dim y As Int = b.Get("y") + 7
		Dim x As Float = b.Get("x")
		If b.ContainsKey("vx") Then
			x = x + b.Get("vx")
			b.Put("x", x)
		End If
		If y > gamePanel.Height Then
			enemyBullets.RemoveAt(i)
		Else
			b.Put("y", y)
		End If
	Next
End Sub

Private Sub UpdatePowerups
	For i = powerups.Size - 1 To 0 Step -1
		Dim p As Map = powerups.Get(i)
		Dim y As Int = p.Get("y") + 3
		If y > gamePanel.Height Then
			powerups.RemoveAt(i)
		Else
			p.Put("y", y)
		End If
	Next
End Sub

Private Sub UpdateParticles
	For i = particles.Size - 1 To 0 Step -1
		Dim p As Map = particles.Get(i)
		Dim life As Int = p.Get("life") - 1
		If life <= 0 Then
			particles.RemoveAt(i)
		Else
			p.Put("x", p.Get("x") + p.Get("vx"))
			p.Put("y", p.Get("y") + p.Get("vy"))
			p.Put("life", life)
		End If
	Next
End Sub

Private Sub CheckCollisions
	' Player bullets hit invaders/boss
	For i = playerBullets.Size - 1 To 0 Step -1
		Dim b As Map = playerBullets.Get(i)
		Dim bx As Int = b.Get("x")
		Dim by As Int = b.Get("y")
		Dim hit As Boolean = False
		
		If isBossWave And bossActive Then
			If bx >= bossX And bx <= bossX + bossWidth And by >= bossY And by <= bossY + bossHeight Then
				bossHealth = bossHealth - 1
				CreateExplosion(bx, by, COLOR_BOSS)
				AddCombo
				hit = True
				If bossHealth <= 0 Then
					bossActive = False
					currentScore = currentScore + SCORE_BOSS * comboMultiplier
					CreateExplosion(bossX + bossWidth/2, bossY + bossHeight/2, COLOR_BOSS)
					SpawnPowerup(bossX + bossWidth/2, bossY + bossHeight/2)
				End If
			End If
		Else
			For Each inv As Map In invaders
				If inv.Get("alive") Then
					Dim ix As Int = inv.Get("x")
					Dim iy As Int = inv.Get("y")
					If bx >= ix And bx <= ix + 30 And by >= iy And by <= iy + 25 Then
						inv.Put("alive", False)
						currentScore = currentScore + SCORE_INVADER * comboMultiplier
						CreateExplosion(ix + 15, iy + 12, COLOR_INVADER)
						AddCombo
						If Rnd(0, 100) < 20 Then SpawnPowerup(ix + 15, iy + 12)
						soundManager.PlayLineClearSound
						hit = True
						Exit
					End If
				End If
			Next
		End If
		
		If hit Then playerBullets.RemoveAt(i)
	Next
	
	' Enemy bullets hit player
	For i = enemyBullets.Size - 1 To 0 Step -1
		Dim bb As Map = enemyBullets.Get(i)
		Dim bxx As Float = bb.Get("x")
		Dim byy As Int = bb.Get("y")
		If bx >= playerX And bxx <= playerX + playerWidth And byy >= playerY And byy <= playerY + playerHeight Then
			lives = lives - 1
			CreateExplosion(playerX + playerWidth/2, playerY + playerHeight/2, COLOR_PLAYER)
			comboCount = 0
			comboMultiplier = 1.0
			enemyBullets.RemoveAt(i)
			If lives <= 0 Then TriggerGameOver
		End If
	Next
	
	' Player collects powerups
	For i = powerups.Size - 1 To 0 Step -1
		Dim p As Map = powerups.Get(i)
		Dim px As Int = p.Get("x")
		Dim py As Int = p.Get("y")
		If px >= playerX And px <= playerX + playerWidth And py >= playerY And py <= playerY + playerHeight Then
			activePowerup = p.Get("type")
			powerupTimer = POWERUP_DURATION
			If activePowerup = "SHIELD" Then
				For Each s As Map In shields
					s.Put("health", 10)
				Next
			End If
			powerups.RemoveAt(i)
			soundManager.PlayLineClearSound
		End If
	Next
End Sub

Private Sub AddCombo
	comboCount = comboCount + 1
	comboTimer = COMBO_TIMEOUT
	comboMultiplier = Min(5.0, 1.0 + (comboCount * 0.2))
End Sub

Private Sub CreateExplosion(x As Float, y As Float, color As Int)
	For i = 0 To 10
		Dim p As Map
		p.Initialize
		p.Put("x", x)
		p.Put("y", y)
		p.Put("vx", (Rnd(-10, 11) / 10.0) * 3)
		p.Put("vy", (Rnd(-10, 11) / 10.0) * 3)
		p.Put("life", Rnd(15, 30))
		p.Put("color", color)
		particles.Add(p)
	Next
End Sub

Private Sub SpawnPowerup(x As Float, y As Float)
	Dim p As Map
	p.Initialize
	p.Put("x", x)
	p.Put("y", y)
	p.Put("type", Array("RAPID", "SHIELD")(Rnd(0, 2)))
	powerups.Add(p)
End Sub

Private Sub CheckWinCondition
	If isBossWave Then
		If Not(bossActive) Then NextWave
	Else
		Dim allDead As Boolean = True
		For Each inv As Map In invaders
			If inv.Get("alive") Then
				allDead = False
				If inv.Get("y") >= playerY - 30 Then TriggerGameOver
			End If
		Next
		If allDead Then NextWave
	End If
End Sub

Private Sub NextWave
	currentWave = currentWave + 1
	If invaderMoveInterval > 5 Then invaderMoveInterval = invaderMoveInterval - 1
	InitializeWave
	playerBullets.Clear
	enemyBullets.Clear
End Sub

'========================================
' PLAYER CONTROLS
'========================================
Public Sub MovePlayer(direction As Int)
	playerTargetX = playerTargetX + (direction * 20)
	playerTargetX = Max(0, Min(gamePanel.Width - playerWidth, playerTargetX))
End Sub

Public Sub SetPlayerMovement(movement As Int)
	' Continuous movement (for touch hold)
End Sub

Public Sub FireBullet
	Dim maxBullets As Int = 3
	If activePowerup = "RAPID" Then maxBullets = 6
	If playerBullets.Size < maxBullets Then
		Dim b As Map
		b.Initialize
		b.Put("x", playerX + playerWidth/2)
		b.Put("y", playerY)
		playerBullets.Add(b)
	End If
End Sub

'========================================
' RENDERING
'========================================
Public Sub Draw
	gameCanvas.DrawColor(COLOR_BACKGROUND)
	
	' Draw boss or invaders
	If isBossWave And bossActive Then
		Dim rect As Rect
		rect.Initialize(bossX, bossY, bossX + bossWidth, bossY + bossHeight)
		gameCanvas.DrawRect(rect, COLOR_BOSS, True, 1)
		' Health bar
		Dim hpWidth As Int = (bossWidth * bossHealth) / bossMaxHealth
		Dim hpRect As Rect
		hpRect.Initialize(bossX, bossY - 8, bossX + hpWidth, bossY - 4)
		gameCanvas.DrawRect(hpRect, Colors.Red, True, 1)
	Else
		For Each inv As Map In invaders
			If inv.Get("alive") Then
				Dim rect As Rect
				rect.Initialize(inv.Get("x"), inv.Get("y"), inv.Get("x") + 30, inv.Get("y") + 25)
				gameCanvas.DrawRect(rect, COLOR_INVADER, True, 1)
			End If
		Next
	End If
	
	' Draw shields
	For Each s As Map In shields
		Dim alpha As Int = Min(255, s.Get("health") * 25)
		Dim color As Int = Colors.ARGB(alpha, 0, 255, 255)
		Dim rect As Rect
		rect.Initialize(s.Get("x"), s.Get("y"), s.Get("x") + 60, s.Get("y") + 30)
		gameCanvas.DrawRect(rect, color, True, 1)
	Next
	
	' Draw player
	Dim rect As Rect
	rect.Initialize(playerX, playerY, playerX + playerWidth, playerY + playerHeight)
	gameCanvas.DrawRect(rect, COLOR_PLAYER, True, 1)
	If activePowerup = "SHIELD" Then
		Dim outline As Rect
		outline.Initialize(playerX - 3, playerY - 3, playerX + playerWidth + 3, playerY + playerHeight + 3)
		gameCanvas.DrawRect(outline, Colors.Cyan, False, 2)
	End If
	
	' Draw bullets
	For Each b As Map In playerBullets
		Dim br As Rect
		br.Initialize(b.Get("x") - 2, b.Get("y") - 6, b.Get("x") + 2, b.Get("y") + 6)
		gameCanvas.DrawRect(br, Colors.Yellow, True, 1)
	Next
	For Each b As Map In enemyBullets
		Dim br As Rect
		br.Initialize(b.Get("x") - 2, b.Get("y") - 6, b.Get("x") + 2, b.Get("y") + 6)
		gameCanvas.DrawRect(br, Colors.Red, True, 1)
	Next
	
	' Draw powerups
	For Each p As Map In powerups
		Dim pr As Rect
		pr.Initialize(p.Get("x") - 8, p.Get("y") - 8, p.Get("x") + 8, p.Get("y") + 8)
		gameCanvas.DrawRect(pr, COLOR_POWERUP, True, 1)
	Next
	
	' Draw particles
	For Each p As Map In particles
		Dim life As Int = p.Get("life")
		Dim alpha As Int = Min(255, life * 8)
		Dim color As Int = p.Get("color")
		Dim particleColor As Int = Colors.ARGB(alpha, _
			Bit.And(Bit.UnsignedShiftRight(color, 16), 0xFF), _
			Bit.And(Bit.UnsignedShiftRight(color, 8), 0xFF), _
			Bit.And(color, 0xFF))
		Dim px As Float = p.Get("x")
		Dim py As Float = p.Get("y")
		Dim pr As Rect
		pr.Initialize(px - 2, py - 2, px + 2, py + 2)
		gameCanvas.DrawRect(pr, particleColor, True, 1)
	Next
	
	gamePanel.Invalidate
End Sub

Private Sub TriggerGameOver
	IsActive = False
	soundManager.StopMusic
	If currentScore > highScore Then highScore = currentScore
	CallSubDelayed2(SpaceInvaders, "HandleGameOver", Array As Object(currentScore, currentWave))
End Sub

Private Sub LoadHighScore As Int
	Return 0
End Sub

Public Sub GetScore As Int
	Return currentScore
End Sub

Public Sub GetWave As Int
	Return currentWave
End Sub

Public Sub GetLives As Int
	Return lives
End Sub

Public Sub GetHighScore As Int
	Return highScore
End Sub