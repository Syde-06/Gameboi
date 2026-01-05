B4A=true
Group=Tetris
ModulesStructureVersion=1
Type=Class
Version=13.4
@EndOfDesignText@
'========================================
' COLLISION MANAGER CLASS
' Handles collision detection logic
'========================================
Sub Class_Globals
	Private grid As Grid
End Sub

Public Sub Initialize(gameGrid As Grid)
	grid = gameGrid
End Sub

'========================================
' COLLISION DETECTION
'========================================
Public Sub IsValidPosition(pieceX As Int, pieceY As Int, shape(,) As Int) As Boolean
	For row = 0 To 3
		For col = 0 To 3
			Try
				If shape(col, row) = 1 Then
					Dim gridX As Int = pieceX + col
					Dim gridY As Int = pieceY + row
                    
					' Check horizontal bounds
					If gridX < 0 Or gridX >= grid.GetWidth Then
						Return False
					End If
                    
					' Check bottom bound
					If gridY >= grid.GetHeight Then
						Return False
					End If
                    
					' Check collision with locked blocks
					If gridY >= 0 And grid.GetCell(gridX, gridY) > 0 Then
						Return False
					End If
				End If
			Catch
				' Ignore array index errors
			End Try
		Next
	Next
    
	Return True
End Sub

Public Sub WouldCollideBelow(pieceX As Int, pieceY As Int, shape(,) As Int) As Boolean
	Return Not(IsValidPosition(pieceX, pieceY + 1, shape))
End Sub

Public Sub WouldCollideLeft(pieceX As Int, pieceY As Int, shape(,) As Int) As Boolean
	Return Not(IsValidPosition(pieceX - 1, pieceY, shape))
End Sub

Public Sub WouldCollideRight(pieceX As Int, pieceY As Int, shape(,) As Int) As Boolean
	Return Not(IsValidPosition(pieceX + 1, pieceY, shape))
End Sub