B4A=true
Group=MultiGame
ModulesStructureVersion=1
Type=Activity
Version=13.4
@EndOfDesignText@
'========================================
' SPACE INVADERS GAME
' Classic arcade shooter gameplay
'========================================

#Region  Activity Attributes 
    #FullScreen: True
    #IncludeTitle: False
#End Region

Sub Process_Globals
	Private soundmgr As SoundManager
	Private gameLoopTimer As Timer
	Private Const FRAME_RATE_MS As Int = 35  ' FASTER: Was 50ms (20 FPS), now 35ms (~28 FPS)
End Sub

Sub Globals
	' UI Components
	Private pnlGameBoard As Panel
	Private lblScore As Label
	Private lblLevel As Label
	Private lblLives As Label
	Private lblHighScore As Label
	Private lblPause As Label
	
	' Control buttons
	Private btnLeft As Button
	Private btnRight As Button
	Private btnFire As Button
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
	
	Private spaceGame As SpaceInvadersGameManager
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("SpaceInvLayout")
	
	InitializeComponents(FirstTime)
	InitializeGameOverDialog
	InitializeSettingsDialog
	gameLoopTimer.Initialize("gameLoopTimer", FRAME_RATE_MS)
	
	StartNewGame
End Sub

Sub Activity_Resume
	Try
		If spaceGame.IsInitialized And gameLoopTimer.IsInitialized Then
			If spaceGame.IsActive And Not(spaceGame.IsPausedState) Then
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
		If spaceGame.IsInitialized Then spaceGame.Pause
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
	
	spaceGame.Initialize(pnlGameBoard)
	
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
	spaceGame.Update
	RefreshDisplay
End Sub

Private Sub RefreshDisplay
	spaceGame.Draw
	UpdateStatistics
End Sub

Private Sub UpdateStatistics
	lblScore.Text = "Score: " & CRLF & spaceGame.GetScore
	lblLevel.Text = "Wave: " & CRLF & spaceGame.GetWave
	lblLives.Text = "Lives: " & CRLF & spaceGame.GetLives
	lblHighScore.Text = "High: " & CRLF & spaceGame.GetHighScore
End Sub

'========================================
' GAME CONTROL
'========================================
Private Sub StartNewGame
	spaceGame.StartNewGame
	gameLoopTimer.Enabled = True
	lblPause.Text = "Pause"
End Sub

'========================================
' INPUT HANDLERS
'========================================
Sub btnLeft_Click
	spaceGame.MovePlayer(-1)
End Sub

Sub btnRight_Click
	spaceGame.MovePlayer(1)
End Sub

Sub btnFire_Click
	spaceGame.FireBullet
End Sub

Sub btnLeft_Touch(Action As Int, X As Float, Y As Float)
	If Action = Activity.ACTION_DOWN Then
		spaceGame.SetPlayerMovement(-1)
	Else If Action = Activity.ACTION_UP Then
		spaceGame.SetPlayerMovement(0)
	End If
End Sub

Sub btnRight_Touch(Action As Int, X As Float, Y As Float)
	If Action = Activity.ACTION_DOWN Then
		spaceGame.SetPlayerMovement(1)
	Else If Action = Activity.ACTION_UP Then
		spaceGame.SetPlayerMovement(0)
	End If
End Sub

Sub btnPause_Click
	If spaceGame.IsPausedState Then
		spaceGame.Resume
		soundmgr.ResumeMusic
		lblPause.Text = "Pause"
		gameLoopTimer.Enabled = True
	Else
		spaceGame.Pause
		soundmgr.PauseMusic
		lblPause.Text = "Resume"
		gameLoopTimer.Enabled = False
	End If
End Sub

Sub btnRestart_Click
	StartNewGame
End Sub

Sub btnMenu_Click
	spaceGame.Pause
	gameLoopTimer.Enabled = False
	soundmgr.PauseMusic
	Activity.Finish
End Sub

Sub btnLeaderboard_Click
	spaceGame.Pause
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
		Dim wave As Int = data(1)
		
		ShowGameOverDialog(score, wave)
	Catch
		Log("Error in HandleGameOver: " & LastException.Message)
	End Try
End Sub

Private Sub ShowGameOverDialog(score As Int, wave As Int)
	lblGameOverTitle.Text = "GAME OVER!"
	lblGameOverScore.Text = "Score: " & score
	lblGameOverLevel.Text = "Wave: " & wave
	
	txtPlayerName.Text = "Player"
	txtPlayerName.SelectAll
	
	pnlGameOver1.Visible = True
	pnlGameOver1.BringToFront
	txtPlayerName.RequestFocus
	
	btnSaveScore.Enabled = True
	btnSaveScore.Text = "Save Score"
	btnSaveScore.Tag = score & "," & wave & ",0"
End Sub

Private Sub HideGameOverDialog
	pnlGameOver1.Visible = False
End Sub

Sub btnSaveScore_Click
	Dim scoreData() As String = Regex.Split(",", btnSaveScore.Tag)
	Dim score As Int = scoreData(0)
	Dim wave As Int = scoreData(1)
	
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
	spaceGame.Pause
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