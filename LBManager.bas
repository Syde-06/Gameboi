B4A=true
Group=Tetris
ModulesStructureVersion=1
Type=Class
Version=13.4
@EndOfDesignText@
'========================================
' LEADERBOARD MANAGER CLASS
' Manages high scores with SQLite database
' Now supports separate leaderboards for different games
'========================================
Sub Class_Globals
	Private sql As SQL
	Private DB_NAME As String = "Database.db"
	Public currentTableName As String = "leaderboard_tetris"
	Private MAX_NAME_LENGTH As Int = 20
	
	' Table names for different games
	Public Const TABLE_TETRIS As String = "leaderboard_tetris"
	Public Const TABLE_SNAKE As String = "leaderboard_snake"
	Public Const TABLE_SPACE As String = "leaderboard_space"
End Sub

Public Sub Initialize
	Initialize2("")
End Sub

Public Sub Initialize2(gameName As String)
	sql.Initialize(File.DirInternal, DB_NAME, True)
	Select gameName.ToLowerCase
		Case "tetris"
			currentTableName = TABLE_TETRIS
		Case "snake"
			currentTableName = TABLE_SNAKE
		Case "space", "spaceinvaders", "invaders"
			currentTableName = TABLE_SPACE
		Case Else
			currentTableName = TABLE_TETRIS
	End Select
	CreateAllTables
End Sub

'========================================
' DATABASE SETUP
'========================================
Private Sub CreateAllTables
	' Create all three game tables
	CreateTableIfNeeded(TABLE_TETRIS)
	CreateTableIfNeeded(TABLE_SNAKE)
	CreateTableIfNeeded(TABLE_SPACE)
End Sub

Private Sub CreateTableIfNeeded(tableName As String)
	Try
		Dim query As String = _
            "CREATE TABLE IF NOT EXISTS " & tableName & " (" & _
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " & _
            "player_name TEXT NOT NULL, " & _
            "score INTEGER NOT NULL, " & _
			"date_played TEXT Not Null)"
        
		sql.ExecNonQuery(query)
		Log("Leaderboard table initialized successfully: " & tableName)
	Catch
		Log("Error creating leaderboard table: " & LastException.Message)
	End Try
End Sub

'========================================
' ADD SCORE
'========================================
Public Sub AddScore(playerName As String, score As Int) As Boolean
	Try
		' Sanitize player name
		If playerName.Trim.Length = 0 Then
			playerName = "Anonymous"
		End If
        
		If playerName.Length > MAX_NAME_LENGTH Then
			playerName = playerName.SubString2(0, MAX_NAME_LENGTH)
		End If
        
		' Get current timestamp
		Dim timestamp As String = DateTime.Date(DateTime.Now) & " " & DateTime.Time(DateTime.Now)
        
		' Insert into database
		Dim query As String = _
            "INSERT INTO " & currentTableName & " (player_name, score, date_played) " & _
            "VALUES (?, ?, ?)"
        
		sql.ExecNonQuery2(query, Array As Object(playerName, score, timestamp))
        
		Log("Score added successfully to " & currentTableName & ": " & playerName & " - " & score)
		Return True
	Catch
		Log("Error adding score: " & LastException.Message)
		Return False
	End Try
End Sub


'========================================
' RETRIEVE SCORES
'========================================
Public Sub GetTopScores(limit As Int) As List
	Dim topScores As List
	topScores.Initialize
    
	Try
		Dim query As String = _
            "SELECT player_name, score, date_played " & _
            "FROM " & currentTableName & " " & _
            "ORDER BY score DESC " & _
            "LIMIT ?"
        
		Dim cursor As Cursor = sql.ExecQuery2(query, Array As String(limit))
        
		For i = 0 To cursor.RowCount - 1
			cursor.Position = i
            
			Dim scoreData As Map
			scoreData.Initialize
			scoreData.Put("player_name", cursor.GetString("player_name"))
			scoreData.Put("score", cursor.GetInt("score"))
			scoreData.Put("date_played", cursor.GetString("date_played"))
            
			topScores.Add(scoreData)
		Next
        
		cursor.Close
	Catch
		Log("Error retrieving top scores: " & LastException.Message)
	End Try
    
	Return topScores
End Sub

'========================================
' SCORE VALIDATION
'========================================
Public Sub IsHighScore(score As Int, topN As Int) As Boolean
	Try
		' Check total number of scores
		Dim totalScores As Int = GetTotalScoresCount
        
		' If fewer than topN scores exist, any score qualifies
		If totalScores < topN Then
			Return True
		End If
        
		' Check if score beats the lowest in top N
		Dim query As String = _
            "SELECT score FROM " & currentTableName & " " & _
            "ORDER BY score DESC LIMIT ?"
        
		Dim cursor As Cursor = sql.ExecQuery2(query, Array As String(topN))
        
		Dim lowestTopScore As Int = 0
		If cursor.RowCount > 0 Then
			cursor.Position = cursor.RowCount - 1
			lowestTopScore = cursor.GetInt("score")
		End If
		cursor.Close
        
		Return score > lowestTopScore
	Catch
		Log("Error checking high score status: " & LastException.Message)
		Return False
	End Try
End Sub

'========================================
' RANKING
'========================================
Public Sub GetPlayerRank(score As Int) As Int
	Try
		Dim query As String = _
            "SELECT COUNT(*) + 1 as rank " & _
            "FROM " & currentTableName & " " & _
            "WHERE score > ?"
        
		Dim cursor As Cursor = sql.ExecQuery2(query, Array As String(score))
		cursor.Position = 0
		Dim rank As Int = cursor.GetInt("rank")
		cursor.Close
        
		Return rank
	Catch
		Log("Error calculating player rank: " & LastException.Message)
		Return -1
	End Try
End Sub

'========================================
' STATISTICS
'========================================
Public Sub GetTotalScoresCount As Int
	Try
		Dim query As String = "SELECT COUNT(*) as count FROM " & currentTableName
        
		Dim cursor As Cursor = sql.ExecQuery(query)
		cursor.Position = 0
		Dim count As Int = cursor.GetInt("count")
		cursor.Close
        
		Return count
	Catch
		Log("Error getting total score count: " & LastException.Message)
		Return 0
	End Try
End Sub

Public Sub GetPlayerBestScore(playerName As String) As Int
	Try
		Dim query As String = _
            "SELECT MAX(score) as best_score " & _
            "FROM " & currentTableName & " " & _
            "WHERE player_name = ?"
        
		Dim cursor As Cursor = sql.ExecQuery2(query, Array As String(playerName))
        
		Dim bestScore As Int = 0
		If cursor.RowCount > 0 Then
			cursor.Position = 0
			Try
				bestScore = cursor.GetInt("best_score")
			Catch
				' NULL value returns 0
				bestScore = 0
			End Try
		End If
        
		cursor.Close
		Return bestScore
	Catch
		Log("Error retrieving player's best score: " & LastException.Message)
		Return 0
	End Try
End Sub

'========================================
' MAINTENANCE
'========================================
Public Sub ClearAllScores
	Try
		Dim query As String = "DELETE FROM " & currentTableName
		sql.ExecNonQuery(query)
		Log("All scores cleared from leaderboard: " & currentTableName)
	Catch
		Log("Error clearing scores: " & LastException.Message)
	End Try
End Sub

Public Sub GetCurrentGame As String
	Select currentTableName
		Case TABLE_TETRIS
			Return "Tetris"
		Case TABLE_SNAKE
			Return "Snake"
		Case TABLE_SPACE
			Return "Space Invaders"
		Case Else
			Return "Unknown"
	End Select
End Sub

Public Sub Close
	Try
		If sql.IsInitialized Then
			sql.Close
			Log("Database connection closed")
		End If
	Catch
		Log("Error closing database: " & LastException.Message)
	End Try
End Sub