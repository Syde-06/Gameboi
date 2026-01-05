B4A=true
Group=Tetris
ModulesStructureVersion=1
Type=Class
Version=13.4
@EndOfDesignText@
'========================================
' INPUT CONTROLLER CLASS
' Translates user input to game actions
'========================================
Sub Class_Globals
	Private gameMgr As GameManager
End Sub

Public Sub Initialize(gameManagerInstance As GameManager)
	gameMgr = gameManagerInstance
End Sub

'========================================
' INPUT HANDLERS
'========================================
Public Sub HandleMoveLeft
	gameMgr.MovePieceBy(-1, 0)
End Sub

Public Sub HandleMoveRight
	gameMgr.MovePieceBy(1, 0)
End Sub

Public Sub HandleRotate
	gameMgr.RotatePiece
End Sub

Public Sub HandleSoftDropStart
	gameMgr.SetSoftDrop(True)
End Sub

Public Sub HandleSoftDropEnd
	gameMgr.SetSoftDrop(False)
End Sub

Public Sub HandleHardDrop
	gameMgr.ExecuteHardDrop
End Sub