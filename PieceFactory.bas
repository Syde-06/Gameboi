B4A=true
Group=Tetris
ModulesStructureVersion=1
Type=Class
Version=13.4
@EndOfDesignText@
'========================================
' PIECE FACTORY CLASS
' Creates Tetromino pieces with proper shapes
'========================================
Sub Class_Globals
	' Piece type constants
	Private Const TYPE_I As Int = 1
	Private Const TYPE_O As Int = 2
	Private Const TYPE_T As Int = 3
	Private Const TYPE_S As Int = 4
	Private Const TYPE_Z As Int = 5
	Private Const TYPE_J As Int = 6
	Private Const TYPE_L As Int = 7
End Sub

Public Sub Initialize
	' Random seed is automatic in B4A
End Sub

'========================================
' PIECE CREATION
'========================================
Public Sub CreateRandomPiece As Tetromino
	Dim pieceType As Int = Rnd(1, 8)  ' 1 to 7
	Return CreatePieceOfType(pieceType)
End Sub

Public Sub CreatePieceOfType(pieceType As Int) As Tetromino
	Dim piece As Tetromino
	piece.Initialize
    
	Dim shape(,) As Int
    
	Select pieceType
		Case TYPE_I: shape = CreateIShape
		Case TYPE_O: shape = CreateOShape
		Case TYPE_T: shape = CreateTShape
		Case TYPE_S: shape = CreateSShape
		Case TYPE_Z: shape = CreateZShape
		Case TYPE_J: shape = CreateJShape
		Case TYPE_L: shape = CreateLShape
		Case Else: shape = CreateTShape
	End Select
	
	piece.Initialize2(pieceType, shape)
	Return piece
End Sub

'========================================
' SHAPE DEFINITIONS
'========================================
Private Sub CreateIShape As Int(,)
	' ####
	Dim s(4, 4) As Int
	For i = 0 To 3
		For j = 0 To 3
			s(i, j) = 0
		Next
	Next
    
	s(0, 1) = 1
	s(1, 1) = 1
	s(2, 1) = 1
	s(3, 1) = 1
    
	Return s
End Sub

Private Sub CreateOShape As Int(,)
	' ##
	' ##
	Dim s(2, 2) As Int
	s(0, 0) = 1
	s(1, 0) = 1
	s(0, 1) = 1
	s(1, 1) = 1
	Return s
End Sub

Private Sub CreateTShape As Int(,)
	'  #
	' ###
	Dim s(3, 2) As Int
	For i = 0 To 2
		For j = 0 To 1
			s(i, j) = 0
		Next
	Next
    
	s(1, 0) = 1
	s(0, 1) = 1
	s(1, 1) = 1
	s(2, 1) = 1
    
	Return s
End Sub

Private Sub CreateSShape As Int(,)
	'  ##
	' ##
	Dim s(3, 2) As Int
	For i = 0 To 2
		For j = 0 To 1
			s(i, j) = 0
		Next
	Next
    
	s(1, 0) = 1
	s(2, 0) = 1
	s(0, 1) = 1
	s(1, 1) = 1
    
	Return s
End Sub

Private Sub CreateZShape As Int(,)
	' ##
	'  ##
	Dim s(3, 2) As Int
	For i = 0 To 2
		For j = 0 To 1
			s(i, j) = 0
		Next
	Next
    
	s(0, 0) = 1
	s(1, 0) = 1
	s(1, 1) = 1
	s(2, 1) = 1
    
	Return s
End Sub

Private Sub CreateJShape As Int(,)
	' #
	' ###
	Dim s(3, 2) As Int
	For i = 0 To 2
		For j = 0 To 1
			s(i, j) = 0
		Next
	Next
    
	s(0, 0) = 1
	s(0, 1) = 1
	s(1, 1) = 1
	s(2, 1) = 1
    
	Return s
End Sub

Private Sub CreateLShape As Int(,)
	'   #
	' ###
	Dim s(3, 2) As Int
	For i = 0 To 2
		For j = 0 To 1
			s(i, j) = 0
		Next
	Next
    
	s(2, 0) = 1
	s(0, 1) = 1
	s(1, 1) = 1
	s(2, 1) = 1
    
	Return s
End Sub