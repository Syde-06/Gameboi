B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=13.4
@EndOfDesignText@
'========================================
' SNAKE GAME MANAGER CLASS
' Manages Snake game state and logic
'========================================
'========================================
' SNAKE GAME MANAGER CLASS
' Manages Snake game state and logic
'========================================
Sub Class_Globals
	Private gameCanvas As Canvas
	Private gamePanel As Panel
	Private soundManager As SoundManager
	
	Public IsActive As Boolean
	Public IsPausedState As Boolean
	Private currentScore As Int
	Private currentLevel As Int
	Private highScore As Int
	
	' Grid
	Private Const GRID_WIDTH As Int = 20
	Private Const GRID_HEIGHT As Int = 50
	Private cellSize As Int
	
	' Snake with smooth animation
	Private snakeBody As List
	Private snakeAnimX As List  ' Smooth interpolated positions
	Private snakeAnimY As List
	Private Const ANIM_SPEED As Float = 0.5
	
	Private direction As String
	Private nextDirection As String
	Private snakeLength As Int
	
	' Food
	Private foodX As Int
	Private foodY As Int
	
	' NEW: Obstacles
	Private obstacles As List
	Private obstacleCount As Int
	
	' NEW: Particles
	Private particles As List
	
	' Colors
	Private Const COLOR_BACKGROUND As Int = 0xFF282828
	Private Const COLOR_SNAKE_HEAD As Int = 0xFF00ff00
	Private Const COLOR_SNAKE_BODY As Int = 0xFF00cc00
	Private Const COLOR_FOOD As Int = 0xFFff0000
	Private Const COLOR_GRID As Int = 0x9BFFFFFF
	Private Const COLOR_OBSTACLE As Int = 0xFF666666
	
	Private Const FOOD_SCORE As Int = 10
	Private Const LEVEL_UP_THRESHOLD As Int = 5
End Sub

Public Sub Initialize(panel As Panel)
	gamePanel = panel
	gameCanvas.Initialize(gamePanel)
	soundManager.Initialize
	soundManager.LoadAudio
	
	cellSize = Min(gamePanel.Width / GRID_WIDTH, gamePanel.Height / GRID_HEIGHT)
	
	snakeBody.Initialize
	snakeAnimX.Initialize
	snakeAnimY.Initialize
	obstacles.Initialize
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
	snakeBody.Clear
	snakeAnimX.Clear
	snakeAnimY.Clear
	obstacles.Clear
	particles.Clear
	
	currentScore = 0
	currentLevel = 1
	snakeLength = 3
	direction = "RIGHT"
	nextDirection = "RIGHT"
	
	' Initialize snake
	Dim startX As Int = GRID_WIDTH / 2
	Dim startY As Int = GRID_HEIGHT / 2
	For i = 0 To snakeLength - 1
		snakeBody.Add(CreatePoint(startX - i, startY))
		snakeAnimX.Add(startX - i)
		snakeAnimY.Add(startY)
	Next
	
	SpawnFood
	UpdateObstacles
End Sub

Private Sub UpdateObstacles
	obstacles.Clear
	
	' Number of obstacles based on level
	obstacleCount = Min(15, (currentLevel - 1) * 2)
	
	For i = 0 To obstacleCount - 1
		Dim validPos As Boolean = False
		Dim ox As Int
		Dim oy As Int
		
		' Find valid position
		Do While Not(validPos)
			ox = Rnd(2, GRID_WIDTH - 2)
			oy = Rnd(2, GRID_HEIGHT - 2)
			validPos = True
			
			' Check if position conflicts with snake or food
			If ox = foodX And oy = foodY Then validPos = False
			For Each segment As Map In snakeBody
				If segment.Get("x") = ox And segment.Get("y") = oy Then
					validPos = False
					Exit
				End If
			Next
			
			' Check if too close to other obstacles
			For Each obs As Map In obstacles
				If Abs(obs.Get("x") - ox) < 2 And Abs(obs.Get("y") - oy) < 2 Then
					validPos = False
					Exit
				End If
			Next
		Loop
		
		obstacles.Add(CreatePoint(ox, oy))
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
	
	' Smooth animation interpolation
	For i = 0 To snakeBody.Size - 1
		Dim segment As Map = snakeBody.Get(i)
		Dim targetX As Int = segment.Get("x")
		Dim targetY As Int = segment.Get("y")
		Dim currentX As Float = snakeAnimX.Get(i)
		Dim currentY As Float = snakeAnimY.Get(i)
		
		' Interpolate
		Dim newX As Float = currentX + (targetX - currentX) * ANIM_SPEED
		Dim newY As Float = currentY + (targetY - currentY) * ANIM_SPEED
		
		' Snap when close enough
		If Abs(targetX - newX) < 0.1 Then newX = targetX
		If Abs(targetY - newY) < 0.1 Then newY = targetY
		
		snakeAnimX.Set(i, newX)
		snakeAnimY.Set(i, newY)
	Next
	
	' Only move snake when animation is complete for head
	Dim headX As Float = snakeAnimX.Get(0)
	Dim headY As Float = snakeAnimY.Get(0)
	Dim headSegment As Map = snakeBody.Get(0)
	Dim targetHeadX As Int = headSegment.Get("x")
	Dim targetHeadY As Int = headSegment.Get("y")
	
	If Abs(headX - targetHeadX) < 0.1 And Abs(headY - targetHeadY) < 0.1 Then
		MoveSnake
	End If
	
	UpdateParticles
End Sub

Private Sub MoveSnake
	direction = nextDirection
	
	Dim head As Map = snakeBody.Get(0)
	Dim newX As Int = head.Get("x")
	Dim newY As Int = head.Get("y")
	
	Select direction
		Case "UP"
			newY = newY - 1
		Case "DOWN"
			newY = newY + 1
		Case "LEFT"
			newX = newX - 1
		Case "RIGHT"
			newX = newX + 1
	End Select
	
	' Check collisions
	If CheckWallCollision(newX, newY) Or _
	   CheckSelfCollision(newX, newY) Or _
	   CheckObstacleCollision(newX, newY) Then
		TriggerGameOver
		Return
	End If
	
	' Check food
	Dim ateFood As Boolean = (newX = foodX And newY = foodY)
	
	' Add new head
	snakeBody.InsertAt(0, CreatePoint(newX, newY))
	snakeAnimX.InsertAt(0, newX)
	snakeAnimY.InsertAt(0, newY)
	
	If ateFood Then
		currentScore = currentScore + (FOOD_SCORE * currentLevel)
		snakeLength = snakeLength + 1
		soundManager.PlayLineClearSound
		CreateFoodParticles(foodX, foodY)
		SpawnFood
		CheckLevelUp
	Else
		' Remove tail
		snakeBody.RemoveAt(snakeBody.Size - 1)
		snakeAnimX.RemoveAt(snakeAnimX.Size - 1)
		snakeAnimY.RemoveAt(snakeAnimY.Size - 1)
	End If
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
			p.Put("vy", p.Get("vy") + 0.1)  ' Gravity
			p.Put("life", life)
		End If
	Next
End Sub

Private Sub CreateFoodParticles(x As Int, y As Int)
	For i = 0 To 8
		Dim p As Map
		p.Initialize
		p.Put("x", x * cellSize + cellSize/2)
		p.Put("y", y * cellSize + cellSize/2)
		p.Put("vx", (Rnd(-10, 11) / 10.0) * 2)
		p.Put("vy", (Rnd(-10, 11) / 10.0) * 2 - 1)
		p.Put("life", Rnd(15, 25))
		particles.Add(p)
	Next
End Sub

'========================================
' COLLISION DETECTION
'========================================
Private Sub CheckWallCollision(x As Int, y As Int) As Boolean
	Return x < 0 Or x >= GRID_WIDTH Or y < 0 Or y >= GRID_HEIGHT
End Sub

Private Sub CheckSelfCollision(x As Int, y As Int) As Boolean
	For i = 0 To snakeBody.Size - 1
		Dim segment As Map = snakeBody.Get(i)
		If segment.Get("x") = x And segment.Get("y") = y Then
			Return True
		End If
	Next
	Return False
End Sub

Private Sub CheckObstacleCollision(x As Int, y As Int) As Boolean
	For Each obs As Map In obstacles
		If obs.Get("x") = x And obs.Get("y") = y Then
			Return True
		End If
	Next
	Return False
End Sub

'========================================
' FOOD & OBSTACLES
'========================================
Private Sub SpawnFood
	Dim validPos As Boolean = False
	
	Do While Not(validPos)
		foodX = Rnd(0, GRID_WIDTH)
		foodY = Rnd(0, GRID_HEIGHT)
		validPos = True
		
		' Check if on snake
		For Each segment As Map In snakeBody
			If segment.Get("x") = foodX And segment.Get("y") = foodY Then
				validPos = False
				Exit
			End If
		Next
		
		' Check if on obstacle
		For Each obs As Map In obstacles
			If obs.Get("x") = foodX And obs.Get("y") = foodY Then
				validPos = False
				Exit
			End If
		Next
	Loop
End Sub

Private Sub CheckLevelUp
	Dim foodsEaten As Int = (snakeLength - 3)
	Dim newLevel As Int = (foodsEaten / LEVEL_UP_THRESHOLD) + 1
	
	If newLevel > currentLevel Then
		currentLevel = newLevel
		UpdateObstacles  ' Add more obstacles
	End If
End Sub

'========================================
' DIRECTION CONTROL
'========================================
Public Sub SetDirection(newDir As String)
	If newDir = "UP" And direction <> "DOWN" Then
		nextDirection = "UP"
	Else If newDir = "DOWN" And direction <> "UP" Then
		nextDirection = "DOWN"
	Else If newDir = "LEFT" And direction <> "RIGHT" Then
		nextDirection = "LEFT"
	Else If newDir = "RIGHT" And direction <> "LEFT" Then
		nextDirection = "RIGHT"
	End If
End Sub

'========================================
' RENDERING
'========================================
Public Sub Draw
	gameCanvas.DrawColor(COLOR_BACKGROUND)
	
	' Draw grid
	For x = 0 To GRID_WIDTH - 1
		For y = 0 To GRID_HEIGHT - 1
			Dim rect As Rect
			rect.Initialize(x * cellSize, y * cellSize, (x + 1) * cellSize, (y + 1) * cellSize)
			gameCanvas.DrawRect(rect, COLOR_GRID, False, 1)
		Next
	Next
	
	' Draw obstacles
	For Each obs As Map In obstacles
		Dim ox As Int = obs.Get("x")
		Dim oy As Int = obs.Get("y")
		Dim rect As Rect
		rect.Initialize(ox * cellSize + 2, oy * cellSize + 2, _
		                (ox + 1) * cellSize - 2, (oy + 1) * cellSize - 2)
		gameCanvas.DrawRect(rect, COLOR_OBSTACLE, True, 1)
		
		' Add X pattern on obstacle
		gameCanvas.DrawLine(ox * cellSize + 4, oy * cellSize + 4, _
		                   (ox + 1) * cellSize - 4, (oy + 1) * cellSize - 4, _
		                   Colors.DarkGray, 2)
		gameCanvas.DrawLine((ox + 1) * cellSize - 4, oy * cellSize + 4, _
		                   ox * cellSize + 4, (oy + 1) * cellSize - 4, _
		                   Colors.DarkGray, 2)
	Next
	
	' Draw food with pulse effect
	Dim foodPulse As Float = 1.0 + (Sin(DateTime.Now / 100.0) * 0.15)
	Dim foodMargin As Int = cellSize * (1 - foodPulse) / 2
	Dim rect As Rect
	rect.Initialize(foodX * cellSize + foodMargin, foodY * cellSize + foodMargin, _
	                (foodX + 1) * cellSize - foodMargin, (foodY + 1) * cellSize - foodMargin)
	gameCanvas.DrawRect(rect, COLOR_FOOD, True, 1)
	
	' Draw snake with smooth animation
	For i = 0 To snakeBody.Size - 1
		Dim xx As Float = snakeAnimX.Get(i)
		Dim yy As Float = snakeAnimY.Get(i)
		Dim color As Int = COLOR_SNAKE_BODY
		If i = 0 Then color = COLOR_SNAKE_HEAD
		
		Dim sr As Rect
		sr.Initialize(xx * cellSize + 2, yy * cellSize + 2, _
		             (xx + 1) * cellSize - 2, (yy + 1) * cellSize - 2)
		gameCanvas.DrawRect(sr, color, True, 1)
	Next
	
	' Draw particles
	For Each p As Map In particles
		Dim life As Int = p.Get("life")
		Dim alpha As Int = Min(255, life * 10)
		Dim pColor As Int = Colors.ARGB(alpha, 255, 0, 0)
		Dim px As Float = p.Get("x")
		Dim py As Float = p.Get("y")
		Dim pr As Rect
		pr.Initialize(px - 2, py - 2, px + 2, py + 2)
		gameCanvas.DrawRect(pr, pColor, True, 1)
	Next
	
	gamePanel.Invalidate
End Sub

'========================================
' GAME OVER
'========================================
Private Sub TriggerGameOver
	IsActive = False
	soundManager.StopMusic
	If currentScore > highScore Then highScore = currentScore
	CallSubDelayed2(Snake, "HandleGameOver", Array As Object(currentScore, currentLevel))
End Sub

Private Sub CreatePoint(x As Int, y As Int) As Map
	Dim point As Map
	point.Initialize
	point.Put("x", x)
	point.Put("y", y)
	Return point
End Sub

'========================================
' GETTERS
'========================================
Public Sub GetScore As Int
	Return currentScore
End Sub

Public Sub GetLevel As Int
	Return currentLevel
End Sub

Public Sub GetHighScore As Int
	Return highScore
End Sub