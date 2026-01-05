B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.4
@EndOfDesignText@
'========================================
' SNAKE GAME - Classic Snake Gameplay
' Control a growing snake to eat food
'========================================

#Region  Activity Attributes 
    #FullScreen: True
    #IncludeTitle: False
#End Region

Sub Process_Globals
	Private soundmgr As SoundManager
	Private gameLoopTimer As Timer
	Private Const FRAME_RATE_MS As Int = 300  ' Snake moves every 300ms
End Sub

Sub Globals
	' UI Components
	Private pnlGameBoard As Panel
	Private lblScore As Label
	Private lblLevel As Label
	Private lblHighScore As Label
	Private lblPause As Label
	
	' Control buttons
	Private btnUp As Button
	Private btnDown As Button
	Private btnLeft As Button
	Private btnRight As Button
	Private btnPause As Button
	Private btnRestart As Button
	Private btnMenu As Button
	Private btnLeaderboard As Button
	
	' Game over dialog
	Private pnlGameOver1 As Panel
	Private pnlGameOver As Panel
	Private pnlDialog As Panel
	Private lblGameOverTitle As Label
	Private lblGameOverScore As Label
	Private lblGameOverLevel As Label
	Private txtPlayerName As EditText
	Private btnSaveScore As Button
	Private snakeGame As SnakeGameManager
	
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
	Activity.LoadLayout("SnakeLayout")
	
	InitializeComponents(FirstTime)
	InitializeGameOverDialog
	InitializeSettingsDialog
	gameLoopTimer.Initialize("gameLoopTimer", FRAME_RATE_MS)
	
	StartNewGame
End Sub

Sub Activity_Resume
	Try
		If snakeGame.IsInitialized And gameLoopTimer.IsInitialized Then
			If snakeGame.IsActive And Not(snakeGame.IsPausedState) Then
				gameLoopTimer.Enabled = True
				soundmgr.ResumeMusic
			End If
		End If
	Catch
		Log("Error in Activity_Resume: " & LastException.Message)
	End Try
End Sub

Sub Activity_Pause(UserClosed As Boolean)
	Try
		If snakeGame.IsInitialized Then snakeGame.Pause
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
	
	snakeGame.Initialize(pnlGameBoard)
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
	snakeGame.Update
	RefreshDisplay
End Sub

Private Sub RefreshDisplay
	snakeGame.Draw
	UpdateStatistics
End Sub

Private Sub UpdateStatistics
	lblScore.Text = "Score: " & CRLF & snakeGame.GetScore
	lblLevel.Text = "Level: " & CRLF & snakeGame.GetLevel
	lblHighScore.Text = "High: " & CRLF & snakeGame.GetHighScore
End Sub

'========================================
' GAME CONTROL
'========================================
Private Sub StartNewGame
	snakeGame.StartNewGame
	gameLoopTimer.Enabled = True
	lblPause.Text = "Pause"
End Sub

'========================================
' INPUT HANDLERS
'========================================
Sub btnUp_Click
	snakeGame.SetDirection("UP")
End Sub

Sub btnDown_Click
	snakeGame.SetDirection("DOWN")
End Sub

Sub btnLeft_Click
	snakeGame.SetDirection("LEFT")
End Sub

Sub btnRight_Click
	snakeGame.SetDirection("RIGHT")
End Sub

Sub btnPause_Click
	If snakeGame.IsPausedState Then
		snakeGame.Resume
		soundmgr.ResumeMusic
		lblPause.Text = "Pause"
		gameLoopTimer.Enabled = True
	Else
		snakeGame.Pause
		soundmgr.PauseMusic
		lblPause.Text = "Resume"
		gameLoopTimer.Enabled = False
	End If
End Sub

Sub btnRestart_Click
	StartNewGame
End Sub

Sub btnMenu_Click
	snakeGame.Pause
	gameLoopTimer.Enabled = False
	soundmgr.PauseMusic
	Activity.Finish
End Sub

Sub btnLeaderboard_Click
	snakeGame.Pause
	gameLoopTimer.Enabled = False
	soundmgr.PauseMusic
	StartActivity(LB)
End Sub

'========================================
' GAME OVER HANDLING
'========================================
Public Sub HandleGameOver(gameOverData As Object)
	Try
		gameLoopTimer.Enabled = False
		soundmgr.StopMusic
		
		Dim data() As Object = gameOverData
		Dim score As Int = data(0)
		Dim level As Int = data(1)
		
		ShowGameOverDialog(score, level)
	Catch
		Log("Error in HandleGameOver: " & LastException.Message)
	End Try
End Sub

Private Sub ShowGameOverDialog(score As Int, level As Int)
	lblGameOverTitle.Text = "GAME OVER!"
	lblGameOverScore.Text = "Score: " & score
	lblGameOverLevel.Text = "Level: " & level
	
	txtPlayerName.Text = "Player"
	txtPlayerName.SelectAll
	
	pnlGameOver1.Visible = True
	pnlGameOver1.BringToFront
	txtPlayerName.RequestFocus
	
	btnSaveScore.Enabled = True
	btnSaveScore.Text = "Save Score"
	btnSaveScore.Tag = score & "," & level & ",0"
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
	snakeGame.Pause
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