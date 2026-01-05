B4A=true
Group=Tetris
ModulesStructureVersion=1
Type=Class
Version=13.4
@EndOfDesignText@
'========================================
' GAME MANAGER CLASS
' Orchestrates game state, scoring, and game loop
'========================================
Sub Class_Globals
	Private grid As Grid
	Private activePiece As Tetromino
	Private nextPiece As Tetromino
	Private collisionManager As CollisionManager
	Private pieceFactory As PieceFactory
	Private soundManager As SoundManager
	
	Public IsActiveState As Boolean
	Public IsPausedState As Boolean
	Private currentScore As Int
	Private currentLevel As Int
	Private totalLinesCleared As Int
	
	' NEW: Combo system
	Private comboCount As Int
	Private comboTimer As Int
	Private comboMultiplier As Float = 1.0
	Private Const COMBO_TIMEOUT As Int = 120  ' Frames before combo resets
	
	' NEW: Smooth piece dropping animation
	Private pieceY As Float  ' Interpolated Y position
	Private pieceTargetY As Int  ' Target Y position
	Private Const DROP_SMOOTHNESS As Float = 0.3
	
	' NEW: Line clear animation
	Private clearingLines As List
	Private clearAnimTimer As Int
	Private Const CLEAR_ANIM_DURATION As Int = 20
	
	' Timing
	Private tickCounter As Int
	Private ticksPerDrop As Int
	Private isSoftDropActive As Boolean
	Private Const BASE_TICKS_PER_DROP As Int = 30
	
	' Scoring with combo bonuses
	Private Const POINTS_SINGLE As Int = 100
	Private Const POINTS_DOUBLE As Int = 300
	Private Const POINTS_TRIPLE As Int = 500
	Private Const POINTS_TETRIS As Int = 800
	Private Const POINTS_SOFT_DROP As Int = 1
	Private Const POINTS_HARD_DROP_ROW As Int = 2
	Private Const COMBO_BONUS_PER_LINE As Int = 50
End Sub

Public Sub Initialize
	grid.Initialize(10, 20)
	collisionManager.Initialize(grid)
	pieceFactory.Initialize
	soundManager.Initialize
	soundManager.LoadAudio
	
	activePiece.Initialize
	nextPiece.Initialize
	clearingLines.Initialize
	
	ResetGameState
End Sub

Public Sub StartNewGame
	ResetGameState
	SpawnNextPiece
	IsActiveState = True
	IsPausedState = False
	soundManager.StartMusic
End Sub

Private Sub ResetGameState
	grid.Clear
	currentScore = 0
	currentLevel = 1
	totalLinesCleared = 0
	tickCounter = 0
	comboCount = 0
	comboTimer = 0
	comboMultiplier = 1.0
	clearAnimTimer = 0
	clearingLines.Clear
	UpdateDropSpeed
	nextPiece = pieceFactory.CreateRandomPiece
End Sub

Public Sub Pause
	IsPausedState = True
	soundManager.PauseMusic
End Sub

Public Sub Resume
	IsPausedState = False
End Sub

Public Sub ResumeAudio
	soundManager.ResumeMusic
End Sub

'========================================
' UPDATE LOOP
'========================================
Public Sub Update
	If Not(IsActiveState) Or IsPausedState Then Return
	
	' Handle line clear animation
	If clearAnimTimer > 0 Then
		clearAnimTimer = clearAnimTimer - 1
		If clearAnimTimer = 0 Then
			CompleteClearAnimation
		End If
		Return  ' Don't update game logic during animation
	End If
	
	' Update combo timer
	If comboTimer > 0 Then
		comboTimer = comboTimer - 1
		If comboTimer = 0 Then
			' Combo expired
			comboCount = 0
			comboMultiplier = 1.0
		End If
	End If
	
	' Smooth piece animation
	If pieceY < pieceTargetY Then
		pieceY = pieceY + (pieceTargetY - pieceY) * DROP_SMOOTHNESS
		If Abs(pieceTargetY - pieceY) < 0.1 Then pieceY = pieceTargetY
	End If
	
	tickCounter = tickCounter + 1
	Dim dropSpeed As Int = CalculateCurrentDropSpeed
	
	If tickCounter >= dropSpeed Then
		tickCounter = 0
		ProcessGravity
	End If
End Sub

Private Sub CalculateCurrentDropSpeed As Int
	If isSoftDropActive Then
		Return Max(2, ticksPerDrop / 4)
	End If
	Return ticksPerDrop
End Sub

Private Sub ProcessGravity
	If Not(MovePieceBy(0, 1)) Then
		' Piece landed
		LockPieceToGrid
		
		Dim completedLines As List = grid.FindCompletedLines
		If completedLines.Size > 0 Then
			soundManager.PlayLineClearSound
			Do While completedLines.Size > 0
				clearingLines = completedLines
				clearAnimTimer = CLEAR_ANIM_DURATION
			Loop
		Else
			comboCount = 0
			comboTimer = 0
			comboMultiplier = 1.0
			SpawnNextPiece
		End If
	Else If isSoftDropActive Then
		currentScore = currentScore + POINTS_SOFT_DROP
	End If
End Sub

Private Sub CompleteClearAnimation
	' Actually clear the lines
	grid.ClearLines(clearingLines)
	
	' Award points with combo bonus
	Dim lineCount As Int = clearingLines.Size
	AwardPoints(lineCount)
	UpdateLevel
	
	' Update combo
	comboCount = comboCount + 1
	comboTimer = COMBO_TIMEOUT
	comboMultiplier = Min(4.0, 1.0 + (comboCount * 0.25))
	
	clearingLines.Clear
	SpawnNextPiece
End Sub

'========================================
' PIECE CONTROL
'========================================
Public Sub MovePieceBy(deltaX As Int, deltaY As Int) As Boolean
	Dim newX As Int = activePiece.GetX + deltaX
	Dim newY As Int = activePiece.GetY + deltaY
	
	If collisionManager.IsValidPosition(newX, newY, activePiece.GetShape) Then
		activePiece.SetPosition(newX, newY)
		pieceTargetY = newY
		If deltaY = 0 Then pieceY = newY  ' Instant horizontal movement
		Return True
	End If
	
	Return False
End Sub

Public Sub RotatePiece
	Dim rotatedShape(,) As Int = activePiece.GetRotatedShape
	
	' Standard rotation
	If collisionManager.IsValidPosition(activePiece.GetX, activePiece.GetY, rotatedShape) Then
		activePiece.Rotate
		Return
	End If
	
	' Wall kick attempts
	Dim kicks() As Int = Array As Int(1, -1, 2, -2)
	For Each offset As Int In kicks
		If collisionManager.IsValidPosition(activePiece.GetX + offset, activePiece.GetY, rotatedShape) Then
			activePiece.SetPosition(activePiece.GetX + offset, activePiece.GetY)
			activePiece.Rotate
			pieceY = activePiece.GetY
			Return
		End If
	Next
	
	' Vertical kick
	If collisionManager.IsValidPosition(activePiece.GetX, activePiece.GetY - 1, rotatedShape) Then
		activePiece.SetPosition(activePiece.GetX, activePiece.GetY - 1)
		activePiece.Rotate
		pieceTargetY = activePiece.GetY
		pieceY = activePiece.GetY
	End If
End Sub

Public Sub SetSoftDrop(enabled As Boolean)
	isSoftDropActive = enabled
End Sub

Public Sub ExecuteHardDrop
	Dim targetY As Int = CalculateDropPosition
	Dim dropDistance As Int = targetY - activePiece.GetY
	
	activePiece.SetPosition(activePiece.GetX, targetY)
	pieceY = targetY
	pieceTargetY = targetY
	currentScore = currentScore + (dropDistance * POINTS_HARD_DROP_ROW)
	
	' Force immediate lock
	tickCounter = ticksPerDrop
End Sub

Public Sub CalculateDropPosition As Int
	Dim testY As Int = activePiece.GetY
	
	Do While collisionManager.IsValidPosition(activePiece.GetX, testY + 1, activePiece.GetShape)
		testY = testY + 1
	Loop
	
	Return testY
End Sub

'========================================
' PIECE MANAGEMENT
'========================================
Private Sub SpawnNextPiece
	activePiece = nextPiece
	nextPiece = pieceFactory.CreateRandomPiece
	
	activePiece.SetPosition(3, -1)
	pieceY = -1
	pieceTargetY = -1
	
	If Not(collisionManager.IsValidPosition(3, -1, activePiece.GetShape)) Then
		TriggerGameOver
	End If
End Sub

Private Sub LockPieceToGrid
	grid.LockPiece(activePiece)
End Sub

Private Sub TriggerGameOver
	IsActiveState = False
	soundManager.StopMusic
	CallSubDelayed2(Tetris, "HandleGameOver", Array As Object(currentScore))
End Sub

'========================================
' SCORING with COMBO SYSTEM
'========================================
Private Sub AwardPoints(lineCount As Int)
	Dim basePoints As Int
	
	Select lineCount
		Case 1: basePoints = POINTS_SINGLE
		Case 2: basePoints = POINTS_DOUBLE
		Case 3: basePoints = POINTS_TRIPLE
		Case 4: basePoints = POINTS_TETRIS
		Case Else: basePoints = 0
	End Select
	
	' Apply level multiplier
	Dim points As Int = basePoints * currentLevel
	
	' Apply combo multiplier
	points = points * comboMultiplier
	
	' Add combo bonus
	If comboCount > 1 Then
		Dim comboBonus As Int = (comboCount - 1) * COMBO_BONUS_PER_LINE * lineCount
		points = points + comboBonus
	End If
	
	currentScore = currentScore + points
	totalLinesCleared = totalLinesCleared + lineCount
End Sub

Private Sub UpdateLevel
	Dim newLevel As Int = (totalLinesCleared / 10) + 1
	If newLevel > currentLevel Then
		currentLevel = newLevel
		UpdateDropSpeed
	End If
End Sub

Private Sub UpdateDropSpeed
	ticksPerDrop = Max(5, BASE_TICKS_PER_DROP - (currentLevel * 2))
End Sub

'========================================
' GETTERS
'========================================
Public Sub GetGrid As Grid
	Return grid
End Sub

Public Sub GetActivePiece As Tetromino
	Return activePiece
End Sub

Public Sub GetNextPiece As Tetromino
	Return nextPiece
End Sub

Public Sub GetScore As Int
	Return currentScore
End Sub

Public Sub GetLevel As Int
	Return currentLevel
End Sub

Public Sub GetLinesCleared As Int
	Return totalLinesCleared
End Sub

Public Sub GetComboCount As Int
	Return comboCount
End Sub

Public Sub GetComboMultiplier As Float
	Return comboMultiplier
End Sub

Public Sub GetPieceAnimY As Float
	Return pieceY
End Sub

Public Sub IsClearingLines As Boolean
	Return clearAnimTimer > 0
End Sub

Public Sub GetClearingLines As List
	Return clearingLines
End Sub

Public Sub GetClearAnimProgress As Float
	If clearAnimTimer = 0 Then Return 0
	Return 1.0 - (clearAnimTimer / CLEAR_ANIM_DURATION)
End Sub