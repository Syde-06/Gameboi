B4A=true
Group=Tetris
ModulesStructureVersion=1
Type=Class
Version=13.4
@EndOfDesignText@
'========================================
' TETROMINO CLASS
' Represents a single Tetris piece
'========================================
Sub Class_Globals
	Private xPos As Int
	Private yPos As Int
	Private pieceType As Int  ' 1-7 (I, O, T, S, Z, J, L)
	Private rotationState As Int  ' 0-3
	Private shape(,) As Int
	Private colorCode As Int
End Sub

Public Sub Initialize
	' Empty piece initialization
	pieceType = 0
	xPos = 0
	yPos = 0
	rotationState = 0
	colorCode = 0
    
	Dim emptyShape(1, 1) As Int
	emptyShape(0, 0) = -1
	shape = emptyShape
End Sub

Public Sub Initialize2(pType As Int, initialShape(,) As Int)
	pieceType = pType
	colorCode = pType
	shape = initialShape
	rotationState = 0
	xPos = 0
	yPos = 0
End Sub

'========================================
' POSITION
'========================================
Public Sub SetPosition(x As Int, y As Int)
	xPos = x
	yPos = y
End Sub

Public Sub GetX As Int
	Return xPos
End Sub

Public Sub GetY As Int
	Return yPos
End Sub

'========================================
' SHAPE & ROTATION
'========================================
Public Sub GetShape As Int(,)
	Return shape
End Sub

Public Sub GetColorCode As Int
	Return colorCode
End Sub

Public Sub GetTypes As Int
    Return pieceType
End Sub

Public Sub Rotate
    rotationState = (rotationState + 1) Mod 4
    shape = RotateMatrix90(shape)
End Sub

Public Sub GetRotatedShape As Int(,)
    Return RotateMatrix90(shape)
End Sub

Private Sub RotateMatrix90(matrix(,) As Int) As Int(,)
    Dim rotated(4, 4) As Int
    
    ' Initialize to zeros
    For row = 0 To 3
        For col = 0 To 3
            rotated(row, col) = 0
        Next
    Next
    
    ' Apply 90° clockwise rotation
    For row = 0 To 3
        For col = 0 To 3
            Try
                rotated(row, col) = matrix(3 - col, row)
            Catch
                ' Ignore bounds errors
            End Try
        Next
    Next
    
    Return rotated
End Sub