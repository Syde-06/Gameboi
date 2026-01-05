B4A=true
Group=Tetris
ModulesStructureVersion=1
Type=Class
Version=13.4
@EndOfDesignText@
'========================================
' RENDERER CLASS
' Handles all visual rendering
'========================================
Sub Class_Globals
	Private cvBoard As Canvas
	Private cvNext As Canvas
	Private pnlBoard As Panel
	Private pnlNext As Panel
	
	Private Const CELL_SIZE As Int = 20dip
	Private Const PREVIEW_CELL_SIZE As Int = 10dip
	
	Private boardOffsetX As Int = 20dip
	Private boardOffsetY As Int = 10%y
	
	Private colorMap As Map
End Sub

Public Sub Initialize(gameBoardPanel As Panel, nextPiecePanel As Panel)
	pnlBoard = gameBoardPanel
	pnlNext = nextPiecePanel
	
	cvBoard.Initialize(pnlBoard)
	cvNext.Initialize(pnlNext)
	
	InitializeColorMap
End Sub

Private Sub InitializeColorMap
	colorMap.Initialize
	colorMap.Put(1, Colors.RGB(0, 240, 240))    ' Cyan - I
	colorMap.Put(2, Colors.RGB(240, 240, 0))    ' Yellow - O
	colorMap.Put(3, Colors.RGB(160, 0, 240))    ' Purple - T
	colorMap.Put(4, Colors.RGB(0, 240, 0))      ' Green - S
	colorMap.Put(5, Colors.RGB(240, 0, 0))      ' Red - Z
	colorMap.Put(6, Colors.RGB(0, 0, 240))      ' Blue - J
	colorMap.Put(7, Colors.RGB(240, 160, 0))    ' Orange - L
End Sub

'========================================
' MAIN RENDERING with COMBO DISPLAY
'========================================
Public Sub DrawGameBoard(grid As Grid, gameMgr As GameManager)
	cvBoard.DrawColor(Colors.RGB(40,40,40))
	
	DrawGrid(grid)
	DrawLockedBlocks(grid, gameMgr)
	DrawGhostPiece(gameMgr.GetActivePiece, grid)
	DrawActivePiece(gameMgr)
	
	' NEW: Draw combo indicator
	If gameMgr.GetComboCount > 1 Then
		DrawComboDisplay(gameMgr.GetComboCount, gameMgr.GetComboMultiplier)
	End If
	
	pnlBoard.Invalidate
End Sub

Public Sub DrawNextPiece(piece As Tetromino)
	cvNext.DrawColor(Colors.RGB(40, 40, 40))
	
	Dim shape(,) As Int = piece.GetShape
	Dim colorCode As Int = piece.GetColorCode
	
	Try
		If shape(0, 0) = -1 Then Return
	Catch
		Return
	End Try
	
	Dim offsetX As Int = (pnlNext.Width - 4 * PREVIEW_CELL_SIZE) / 2
	Dim offsetY As Int = (pnlNext.Height - 4 * PREVIEW_CELL_SIZE) / 2
	
	For row = 0 To 3
		For col = 0 To 3
			Try
				If shape(col, row) = 1 Then
					Dim x As Int = offsetX + col * PREVIEW_CELL_SIZE
					Dim y As Int = offsetY + row * PREVIEW_CELL_SIZE
					DrawBlock(cvNext, x, y, PREVIEW_CELL_SIZE, colorCode, 1.0)
				End If
			Catch
			End Try
		Next
	Next
	
	pnlNext.Invalidate
End Sub

'========================================
' GRID & BLOCKS
'========================================
Private Sub DrawGrid(grid As Grid)
	Dim gridColor As Int = Colors.RGB(255, 255, 255)
	
	For row = 0 To grid.GetHeight - 1
		For col = 0 To grid.GetWidth - 1
			Dim x As Int = boardOffsetX + col * CELL_SIZE
			Dim y As Int = boardOffsetY + row * CELL_SIZE
			
			Dim rect As Rect
			rect.Initialize(x, y, x + CELL_SIZE, y + CELL_SIZE)
			cvBoard.DrawRect(rect, gridColor, False, 1)
		Next
	Next
End Sub

Private Sub DrawLockedBlocks(grid As Grid, gameMgr As GameManager)
	Dim cells(,) As Int = grid.GetCells
	
	For row = 0 To grid.GetHeight - 1
		For col = 0 To grid.GetWidth - 1
			If cells(col, row) > 0 Then
				Dim alpha As Float = 1.0
				
				' NEW: Flash effect for clearing lines
				If gameMgr.IsClearingLines Then
					Dim clearingLines As List = gameMgr.GetClearingLines
					For Each lineY As Int In clearingLines
						If row = lineY Then
							Dim progress As Float = gameMgr.GetClearAnimProgress
							alpha = 1.0 - (Sin(progress * 3.14159) * 0.5)
						End If
					Next
				End If
				
				DrawBlockOnBoard(col, row, cells(col, row), alpha)
			End If
		Next
	Next
End Sub

Private Sub DrawActivePiece(gameMgr As GameManager)
	Try
		Dim piece As Tetromino = gameMgr.GetActivePiece
		Dim shape(,) As Int = piece.GetShape
		If shape(0, 0) = -1 Then Return
		
		Dim pieceX As Int = piece.GetX
		' NEW: Use animated Y position for smooth dropping
		Dim pieceY As Float = gameMgr.GetPieceAnimY
		Dim colorCode As Int = piece.GetColorCode
		
		DrawPieceShapeSmooth(shape, pieceX, pieceY, colorCode, 1.0)
	Catch
	End Try
End Sub

Private Sub DrawGhostPiece(piece As Tetromino, grid As Grid)
	Try
		Dim shape(,) As Int = piece.GetShape
		If shape(0, 0) = -1 Then Return
		
		Dim pieceX As Int = piece.GetX
		Dim ghostY As Int = CalculateGhostY(piece, grid)
		Dim colorCode As Int = piece.GetColorCode
		
		DrawPieceShape(shape, pieceX, ghostY, colorCode, 0.25)
	Catch
	End Try
End Sub

Private Sub CalculateGhostY(piece As Tetromino, grid As Grid) As Int
	Dim testY As Int = piece.GetY
	Dim collisionMgr As CollisionManager
	collisionMgr.Initialize(grid)
	
	Do While collisionMgr.IsValidPosition(piece.GetX, testY + 1, piece.GetShape)
		testY = testY + 1
	Loop
	
	Return testY
End Sub

'========================================
' BLOCK RENDERING
'========================================
Private Sub DrawPieceShape(shape(,) As Int, posX As Int, posY As Int, colorCode As Int, alpha As Float)
	For row = 0 To 3
		For col = 0 To 3
			Try
				If shape(col, row) = 1 Then
					Dim gridX As Int = posX + col
					Dim gridY As Int = posY + row
					
					If gridY >= 0 Then
						DrawBlockOnBoard(gridX, gridY, colorCode, alpha)
					End If
				End If
			Catch
			End Try
		Next
	Next
End Sub

Private Sub DrawPieceShapeSmooth(shape(,) As Int, posX As Int, posY As Float, colorCode As Int, alpha As Float)
	For row = 0 To 3
		For col = 0 To 3
			Try
				If shape(col, row) = 1 Then
					Dim gridX As Int = posX + col
					Dim gridY As Float = posY + row
					
					If gridY >= 0 Then
						DrawBlockOnBoardSmooth(gridX, gridY, colorCode, alpha)
					End If
				End If
			Catch
			End Try
		Next
	Next
End Sub

Private Sub DrawBlockOnBoard(gridX As Int, gridY As Int, colorCode As Int, alpha As Float)
	Dim x As Int = boardOffsetX + gridX * CELL_SIZE
	Dim y As Int = boardOffsetY + gridY * CELL_SIZE
	DrawBlock(cvBoard, x, y, CELL_SIZE, colorCode, alpha)
End Sub

Private Sub DrawBlockOnBoardSmooth(gridX As Int, gridY As Float, colorCode As Int, alpha As Float)
	Dim x As Int = boardOffsetX + gridX * CELL_SIZE
	Dim y As Int = boardOffsetY + (gridY * CELL_SIZE)
	DrawBlock(cvBoard, x, y, CELL_SIZE, colorCode, alpha)
End Sub

Private Sub DrawBlock(canvas As Canvas, x As Int, y As Int, size As Int, colorCode As Int, alpha As Float)
	Dim blockColor As Int = GetColorWithAlpha(colorCode, alpha)
	
	' Draw filled block
	Dim rect As Rect
	rect.Initialize(x + 2, y + 2, x + size - 2, y + size - 2)
	canvas.DrawRect(rect, blockColor, True, 0)
	
	' 3D highlight for solid blocks
	If alpha > 0.5 Then
		Dim highlightColor As Int = Colors.ARGB(150 * alpha, 255, 255, 255)
		canvas.DrawLine(x + 2, y + 2, x + size - 2, y + 2, highlightColor, 2)
		canvas.DrawLine(x + 2, y + 2, x + 2, y + size - 2, highlightColor, 2)
	End If
End Sub

Private Sub GetColorWithAlpha(colorCode As Int, alpha As Float) As Int
	Dim baseColor As Int
	
	If colorMap.ContainsKey(colorCode) Then
		baseColor = colorMap.Get(colorCode)
	Else
		baseColor = Colors.Gray
	End If
	
	Dim a As Int = 255 * alpha
	Dim r As Int = Bit.And(Bit.UnsignedShiftRight(baseColor, 16), 0xFF)
	Dim g As Int = Bit.And(Bit.UnsignedShiftRight(baseColor, 8), 0xFF)
	Dim b As Int = Bit.And(baseColor, 0xFF)
	
	Return Colors.ARGB(a, r, g, b)
End Sub

'========================================
' NEW: COMBO DISPLAY
'========================================
Private Sub DrawComboDisplay(comboCount As Int, multiplier As Float)
	' Position in top-right of game board
	Dim textX As Int = boardOffsetX + (10 * CELL_SIZE) - 80
	Dim textY As Int = boardOffsetY + 20
	
	' Draw combo background with pulsing effect
	Dim pulseSize As Int = 5 * (comboCount Mod 3)
	Dim bgRect As Rect
	bgRect.Initialize(textX - 10 - pulseSize, textY - 5 - pulseSize, _
		textX + 100 + pulseSize, textY + 45 + pulseSize)
	
	' Semi-transparent dark background
	cvBoard.DrawRect(bgRect, Colors.ARGB(180, 50, 50, 50), True, 0)
	
	' Glowing border - multiple colors based on combo count
	Dim borderColor As Int
	If comboCount >= 4 Then
		borderColor = Colors.ARGB(255, 255, 0, 0) ' Red for high combo
	Else If comboCount >= 3 Then
		borderColor = Colors.ARGB(255, 255, 165, 0) ' Orange
	Else
		borderColor = Colors.ARGB(255, 255, 200, 0) ' Yellow
	End If
	cvBoard.DrawRect(bgRect, borderColor, False, 3)
	
	' Draw visual indicator bars for combo level
	For i = 0 To Min(comboCount - 1, 5)
		Dim barRect As Rect
		barRect.Initialize(textX + 5 + (i * 15), textY + 35, _
			textX + 15 + (i * 15), textY + 40)
		cvBoard.DrawRect(barRect, borderColor, True, 0)
	Next
End Sub