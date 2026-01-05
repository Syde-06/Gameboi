B4A=true
Group=Tetris
ModulesStructureVersion=1
Type=Activity
Version=13.4
@EndOfDesignText@
'========================================
' MAIN ACTIVITY - Entry Point
' Coordinates high-level game components
'========================================

#Region  Activity Attributes 
    #FullScreen: True
    #IncludeTitle: False
#End Region

Sub Process_Globals
	' Core game components
	Private gameMgr As GameManager
	Private playerInput As InputController
	Private soundmgr As SoundManager
    
	' UI update timer
	Private gameLoopTimer As Timer
	Private Const FRAME_RATE_MS As Int = 33  ' ~30 FPS
End Sub


Sub Globals
	' Core game components
	Private displayRenderer As Renderer
	
	' Main game display panels
	Private pnlGameBoard As Panel
	Private pnlNextPiece As Panel
	Private pnlControls As Panel
    
	' UI Labels
	Private lblScore As Label
	Private lblLevel As Label
	Private lblLines As Label
	Private lblNextPiece As Label
	Private lblPause As Label
	Private lblRestart As Label
	Private lblSettings As Label
	' Control buttons
	Private btnPause As Button
	Private btnRestart As Button
	Private btnLeaderboard As Button
	Private btnArotate As Button
	Private btnLeft As Button
	Private btnRight As Button
	Private btnDown As Button
	Private btnBdrop As Button
	Private btnSettings As Button
	Private btnMenu As Button
	' Game over dialog
	Private pnlGameOver As Panel
	Private pnlGameOver1 As Panel
	Private pnlDialog As Panel
	Private lblGameOverTitle As Label
	Private lblGameOverScore As Label
	Private lblGameOverLevel As Label
	Private lblGameOverLines As Label
	Private txtPlayerName As EditText
	Private btnSaveScore As Button
	' Settings Dialog
	Private pnlSettings As Panel
	Private pnlSettings1 As Panel
	Private pnlSettingsDialog As Panel
	Private lblSettingsTitle As Label
	Private lblBGMVolume As Label
	Private lblSFXVolume As Label
	Private btnSaveSettings As Button
	Private bgmToggle As ToggleButton
	Private sfxToggle As ToggleButton
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("TetrisLayout")
    
	InitializeComponents(FirstTime)
	InitializeGameOverDialog
	InitializeSettingsDialog
	gameLoopTimer.Initialize("gameLoopTimer", FRAME_RATE_MS)
    
	RefreshDisplay
End Sub

Sub Activity_Resume
	Try
		If gameMgr.IsInitialized And gameLoopTimer.IsInitialized Then
			If gameMgr.IsActiveState And Not(gameMgr.IsPausedState) Then
				gameLoopTimer.Enabled = True
				gameMgr.ResumeAudio
			End If
		End If
	Catch
		Log("Error in Activity_Resume: " & LastException.Message)
	End Try
End Sub

Sub Activity_Pause(UserClosed As Boolean)
	Try
		If gameMgr.IsInitialized Then gameMgr.Pause
		If gameLoopTimer.IsInitialized Then gameLoopTimer.Enabled = False
		soundmgr.PauseMusic
	Catch
		Log("Error in Activity_Pause: " & LastException.Message)
	End Try
End Sub

'========================================
' INITIALIZATION
'========================================
Private Sub InitializeComponents(isFirstTime As Boolean)
	' Initialize core systems
	gameLoopTimer.Initialize("gameLoopTimer", FRAME_RATE_MS)
	
	' Initialize sound manager - check if class instance exists first
	Try
		If soundmgr.IsInitialized = False Then
			soundmgr.Initialize
			soundmgr.LoadAudio
		End If
	Catch
		soundmgr.Initialize
		soundmgr.LoadAudio
	End Try
	
	gameMgr.Initialize
	displayRenderer.Initialize(pnlGameBoard, pnlNextPiece)
	playerInput.Initialize(gameMgr)
	
	StartNewGame
End Sub

Private Sub InitializeGameOverDialog
	pnlGameOver1.Initialize("")
	Activity.AddView(pnlGameOver1, 0, 0, 100%x, 100%y)
	pnlGameOver1.LoadLayout("GameOverDialog")
	pnlGameOver1.Visible = False
End Sub

Private Sub InitializeSettingsDialog
	pnlSettings1.Initialize("")
	Activity.AddView(pnlSettings1, 0, 0, 100%x, 100%y)
	pnlSettings1.LoadLayout("SettingsDialog")
	pnlSettings1.Visible = False
End Sub

'========================================
' GAME LOOP
'========================================
Sub gameLoopTimer_Tick
	gameMgr.Update
	RefreshDisplay
End Sub

Private Sub RefreshDisplay
	displayRenderer.DrawGameBoard(gameMgr.GetGrid, gameMgr)
	displayRenderer.DrawNextPiece(gameMgr.GetNextPiece)
	UpdateStatistics
End Sub

Private Sub UpdateStatistics
	lblScore.Text = "Score: " & CRLF & gameMgr.GetScore
	lblLevel.Text = "Level: " & CRLF & gameMgr.GetLevel
	lblLines.Text = "Lines: " & CRLF & gameMgr.GetLinesCleared
End Sub

'========================================
' GAME CONTROL
'========================================
Private Sub StartNewGame
	gameMgr.StartNewGame
	gameLoopTimer.Enabled = True
	lblPause.Text = "Pause"
End Sub

private Sub ButtonsToggle(tgl As Boolean)
	pnlControls.Enabled = tgl
End Sub

'========================================
' INPUT HANDLERS
'========================================
Sub btnPause_Click
	If gameMgr.IsPausedState Then
		gameMgr.Resume
		soundmgr.StartMusic
		lblPause.Text = "Pause"
		ButtonsToggle(False)
		gameLoopTimer.Enabled = True
	Else
		gameMgr.Pause
		soundmgr.PauseMusic
		lblPause.Text = "Resume"
		ButtonsToggle(True)
		gameLoopTimer.Enabled = False
	End If
End Sub

Sub btnRestart_Click
	StartNewGame
End Sub

Sub btnLeaderboard_Click
	gameMgr.Pause
	lblPause.Text = "Resume"
	soundmgr.PauseMusic
	gameLoopTimer.Enabled = False
	StartActivity(LB)
End Sub

Sub btnArotate_Click
	playerInput.HandleRotate
End Sub

Sub btnLeft_Click
	playerInput.HandleMoveLeft
End Sub

Sub btnRight_Click
	playerInput.HandleMoveRight
End Sub

Sub btnDown_Click
	playerInput.HandleSoftDropStart
End Sub

Sub btnBdrop_Click
	playerInput.HandleHardDrop
End Sub

Sub btnDown_Touch(Action As Int, X As Float, Y As Float)
	If Action = Activity.ACTION_UP Then
		playerInput.HandleSoftDropEnd
	End If
End Sub

Sub btnMenu_Click
	gameMgr.Pause
	gameLoopTimer.Enabled = False
	soundmgr.PauseMusic
	Activity.Finish
End Sub

'========================================
' GAME OVER HANDLING
'========================================
Public Sub HandleGameOver(gameOverData As Object)
	Try
		gameLoopTimer.Enabled = False
        
		Dim data() As Object = gameOverData
		Dim score As Int = data(0)
        
		ShowGameOverDialog(score)
	Catch
		Log("Error in HandleGameOver: " & LastException.Message)
	End Try
End Sub

Private Sub ShowGameOverDialog(score As Int)
	lblGameOverTitle.Text = "GAME OVER!"
	lblGameOverScore.Text = "Score: " & score
    
	txtPlayerName.Text = "Player"
	txtPlayerName.SelectAll
    
	pnlGameOver1.Visible = True
	pnlGameOver1.BringToFront
	txtPlayerName.RequestFocus
    
	btnSaveScore.Enabled = True
	btnSaveScore.Text = "Save Score"
	btnSaveScore.Tag = score
End Sub

Private Sub HideGameOverDialog
	pnlGameOver1.Visible = False
End Sub

Sub btnSaveScore_Click
	Dim scoreData() As String = Regex.Split(",", btnSaveScore.Tag)
	Dim score As Int = scoreData(0)
    
	SavePlayerScore(score)
End Sub

Private Sub SavePlayerScore(score As Int)
	Dim playerName As String = txtPlayerName.Text.Trim
	If playerName.Length = 0 Then playerName = "Anonymous"
	If playerName.Length > 20 Then playerName = playerName.SubString2(0, 20)
    
	Try
		Dim success As Boolean = Starter.leaderboardManager.AddScore(playerName, score)
        
		If success Then
			btnSaveScore.Enabled = False
			btnSaveScore.Text = "Score Saved ✓"
			Sleep(1000)
			HideGameOverDialog
		Else
			ToastMessageShow("Error saving score", False)
		End If
	Catch
		ToastMessageShow("Error saving score", False)
	End Try
End Sub

Private Sub btnSettings_Click
	gameMgr.Pause
	lblPause.Text = "Resume"
	soundmgr.PauseMusic
	gameLoopTimer.Enabled = False
	
	' Load current toggle settings from database into toggles
	bgmToggle.Checked = soundmgr.GetBGMEnabled
	sfxToggle.Checked = soundmgr.GetSFXEnabled
	
	pnlSettings1.Visible = True
	pnlSettings1.BringToFront
End Sub

Private Sub btnSaveSettings_Click
	' Update sound manager with toggle states
	soundmgr.SetBGMEnabled(bgmToggle.Checked)
	soundmgr.SetSFXEnabled(sfxToggle.Checked)
	pnlSettings1.Visible = False
End Sub

Sub bgmToggle_CheckedChange(Checked As Boolean)
End Sub

Sub sfxToggle_CheckedChange(Checked As Boolean)
End Sub