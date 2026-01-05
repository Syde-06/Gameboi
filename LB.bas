B4A=true
Group=Tetris
ModulesStructureVersion=1
Type=Activity
Version=13.4
@EndOfDesignText@
#Region  Activity Attributes 
    #FullScreen: True
    #IncludeTitle: False
#End Region

Sub Process_Globals
End Sub

Sub Globals
	' UI Controls
	Private lvLeaderboard As ListView
	Private btnBack As Button
	Private btnClearScores As Button
	Private lblTitle As Label
	Private lblTotalScores As Label
    
	Private TOP_SCORES_LIMIT As Int = 10
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Leaderboard")
    
	' Note: LBManager should already be initialized by the calling activity
	' with the correct game name
	
	LoadAndDisplayLeaderboard
End Sub

Sub Activity_Resume
	' Refresh leaderboard when returning to this activity
	LoadAndDisplayLeaderboard
End Sub

Sub Activity_Pause(UserClosed As Boolean)
	' No cleanup needed
End Sub

'========================================
' LEADERBOARD DISPLAY
'========================================
Sub LoadAndDisplayLeaderboard
	Try
		lvLeaderboard.Clear
		
		' Update title to show which game's leaderboard
		Dim gameName As String = Starter.leaderboardManager.GetCurrentGame
		lblTitle.Text = gameName & " Leaderboard"
		Activity.Title = gameName & " Leaderboard"
        
		Dim topScores As List = Starter.leaderboardManager.GetTopScores(TOP_SCORES_LIMIT)
        
		If topScores.Size = 0 Then
			lvLeaderboard.AddSingleLine("No scores yet. Play to set a record!")
		Else
			DisplayScores(topScores, gameName)
		End If
        
		UpdateTotalScoresLabel
        
	Catch
		Log("Error loading leaderboard: " & LastException.Message)
		ToastMessageShow("Error loading leaderboard", False)
	End Try
End Sub

Private Sub DisplayScores(scoresList As List, gameName As String)
	For i = 0 To scoresList.Size - 1
		Dim scoreData As Map = scoresList.Get(i)
        
		Dim rank As Int = i + 1
		Dim playerName As String = scoreData.Get("player_name")
		Dim score As Int = scoreData.Get("score")
		Dim datePlayed As String = scoreData.Get("date_played")
        
		' Format primary and secondary text based on game
		Dim primaryText As String = rank & ". " & playerName & " - " & score & " points"
		Dim secondaryText As String
		
		Select gameName
			Case "Tetris"
				secondaryText =  datePlayed
			Case "Snake"
				secondaryText = datePlayed
			Case "Space Invaders"
				secondaryText = datePlayed
			Case Else
				secondaryText = datePlayed
		End Select
        
		lvLeaderboard.AddTwoLines(primaryText, secondaryText)
	Next
End Sub

Private Sub UpdateTotalScoresLabel
	Dim totalGamesPlayed As Int = Starter.leaderboardManager.GetTotalScoresCount
	lblTotalScores.Text = "Total Games Played: " & totalGamesPlayed
End Sub

'========================================
' BUTTON HANDLERS
'========================================
Sub btnBack_Click
	Activity.Finish
End Sub

Sub btnClearScores_Click
	Dim gameName As String = Starter.leaderboardManager.GetCurrentGame
	Dim result As Int = Msgbox2( _
        "Are you sure you want to clear all " & gameName & " scores? This cannot be undone!", _
        "Clear " & gameName & " Leaderboard", _
        "Yes", "", "No", Null)
    
	If result = DialogResponse.POSITIVE Then
		Starter.leaderboardManager.ClearAllScores
		LoadAndDisplayLeaderboard
		ToastMessageShow(gameName & " leaderboard cleared successfully", False)
	End If
End Sub

Sub lvLeaderboard_ItemClick(Position As Int, Value As Object)
	' Optional: Could implement detailed score view here
End Sub