B4A=true
Group=Tetris
ModulesStructureVersion=1
Type=Class
Version=13.4
@EndOfDesignText@
'========================================
' GRID CLASS
' Manages the Tetris playfield
'========================================
Sub Class_Globals
	Private cells(,) As Int  ' 0 = empty, 1-7 = color codes
	Private width As Int
	Private height As Int
End Sub

Public Sub Initialize(gridWidth As Int, gridHeight As Int)
	width = gridWidth
	height = gridHeight
    
	Dim tempCells(width, height) As Int
	cells = tempCells
    
	Clear
End Sub

'========================================
' GRID OPERATIONS
'========================================
Public Sub Clear
	For x = 0 To width - 1
		For y = 0 To height - 1
			cells(x, y) = 0
		Next
	Next
End Sub

Public Sub SetCell(x As Int, y As Int, value As Int)
	If IsInBounds(x, y) Then
		cells(x, y) = value
	End If
End Sub

Public Sub GetCell(x As Int, y As Int) As Int
	If IsInBounds(x, y) Then
		Return cells(x, y)
	End If
	Return 0
End Sub

Public Sub GetCells As Int(,)
	Return cells
End Sub

Public Sub IsInBounds(x As Int, y As Int) As Boolean
	Return x >= 0 And x < width And y >= 0 And y < height
End Sub

Public Sub GetWidth As Int
	Return width
End Sub

Public Sub GetHeight As Int
	Return height
End Sub

'========================================
' PIECE LOCKING
'========================================
Public Sub LockPiece(piece As Tetromino)
	Dim shape(,) As Int = piece.GetShape
	Dim pieceX As Int = piece.GetX
	Dim pieceY As Int = piece.GetY
	Dim colorCode As Int = piece.GetColorCode
    
	For row = 0 To 3
		For col = 0 To 3
			Try
				If shape(col, row) = 1 Then
					Dim gridX As Int = pieceX + col
					Dim gridY As Int = pieceY + row
                    
					If IsInBounds(gridX, gridY) Then
						cells(gridX, gridY) = colorCode
					End If
				End If
			Catch
				' Ignore array bounds errors
			End Try
		Next
	Next
End Sub

'========================================
' LINE CLEARING
'========================================
Public Sub FindCompletedLines As List
	Dim completedLines As List
	completedLines.Initialize
    
	For y = 0 To height - 1
		If IsLineComplete(y) Then
			completedLines.Add(y)
		End If
	Next
    
	Return completedLines
End Sub

Private Sub IsLineComplete(y As Int) As Boolean
	For x = 0 To width - 1
		If cells(x, y) = 0 Then
			Return False
		End If
	Next
	Return True
End Sub

Public Sub ClearLines(lineIndices As List)
	lineIndices.Sort(False)  ' Sort bottom to top
    
	For Each lineY As Int In lineIndices
		RemoveLine(lineY)
	Next
End Sub

Private Sub RemoveLine(lineY As Int)
	' Shift all rows above down by one
	For y = lineY To 1 Step -1
		For x = 0 To width - 1
			cells(x, y) = cells(x, y - 1)
		Next
	Next
    
	' Clear top row
	For x = 0 To width - 1
		cells(x, 0) = 0
	Next
End Sub