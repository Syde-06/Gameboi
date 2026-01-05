B4A=true
Group=Tetris
ModulesStructureVersion=1
Type=Class
Version=13.4
@EndOfDesignText@
'========================================
' SETTINGS MANAGER CLASS
' Manages app settings with SQLite database
'========================================
Sub Class_Globals
	Private sql As SQL
	Private DB_NAME As String = "Database.db"
	Private TABLE_NAME As String = "settings"
	
	' Setting keys
	Private Const KEY_BGM_ENABLED As String = "bgm_enabled"
	Private Const KEY_SFX_ENABLED As String = "sfx_enabled"
	
	' Default values
	Private Const DEFAULT_BGM_ENABLED As Boolean = True
	Private Const DEFAULT_SFX_ENABLED As Boolean = True
End Sub

Public Sub Initialize
	Try
		sql.Initialize(File.DirInternal, DB_NAME, True)
		CreateTableIfNeeded
		InitializeDefaultSettings
		Log("SettingsManager initialized successfully")
	Catch
		Log("Error initializing SettingsManager: " & LastException.Message)
	End Try
End Sub

'========================================
' DATABASE SETUP
'========================================
Private Sub CreateTableIfNeeded
	Try
		Dim query As String
		query = "CREATE TABLE IF NOT EXISTS " & TABLE_NAME & " (" & _
		    "setting_key TEXT PRIMARY KEY, " & _
		    "setting_value TEXT NOT NULL, " & _
		    "last_updated TEXT NOT NULL)"
		
		sql.ExecNonQuery(query)
		Log("Settings table created successfully")
	Catch
		Log("Error creating settings table: " & LastException.Message)
	End Try
End Sub

Private Sub InitializeDefaultSettings
	' Only insert defaults if they don't exist
	If SettingExists(KEY_BGM_ENABLED) = False Then
		SaveSetting(KEY_BGM_ENABLED, DEFAULT_BGM_ENABLED)
	End If
	If SettingExists(KEY_SFX_ENABLED) = False Then
		SaveSetting(KEY_SFX_ENABLED, DEFAULT_SFX_ENABLED)
	End If
End Sub

'========================================
' SAVE SETTINGS
'========================================
Private Sub SaveSetting(key As String, value As Boolean)
	Try
		Dim timestamp As String = DateTime.Date(DateTime.Now) & " " & DateTime.Time(DateTime.Now)
		Dim query As String
		
		' Use INSERT OR REPLACE to handle both insert and update
		query = "INSERT OR REPLACE INTO " & TABLE_NAME & " " & _
		    "(setting_key, setting_value, last_updated) " & _
		    "VALUES (?, ?, ?)"
		
		' Convert boolean to string for storage
		Dim valueStr As String
		If value Then
			valueStr = "1"
		Else
			valueStr = "0"
		End If
		
		sql.ExecNonQuery2(query, Array As Object(key, valueStr, timestamp))
		Log("Setting saved: " & key & " = " & value)
	Catch
		Log("Error saving setting " & key & ": " & LastException.Message)
	End Try
End Sub

Public Sub SaveBGMEnabled(enabled As Boolean)
	SaveSetting(KEY_BGM_ENABLED, enabled)
End Sub

Public Sub SaveSFXEnabled(enabled As Boolean)
	SaveSetting(KEY_SFX_ENABLED, enabled)
End Sub

Public Sub SaveSoundSettings(bgmEnabled As Boolean, sfxEnabled As Boolean)
	SaveBGMEnabled(bgmEnabled)
	SaveSFXEnabled(sfxEnabled)
End Sub

'========================================
' RETRIEVE SETTINGS
'========================================
Private Sub GetSetting(key As String, defaultValue As Boolean) As Boolean
	Try
		Dim query As String
		query = "SELECT setting_value FROM " & TABLE_NAME & " " & _
		    "WHERE setting_key = ?"
		
		Dim cursor As Cursor = sql.ExecQuery2(query, Array As String(key))
		
		If cursor.RowCount > 0 Then
			cursor.Position = 0
			Dim valueStr As String = cursor.GetString("setting_value")
			cursor.Close
			
			' Convert string to boolean
			Return valueStr = "1"
		Else
			cursor.Close
			Return defaultValue
		End If
	Catch
		Log("Error retrieving setting " & key & ": " & LastException.Message)
		Return defaultValue
	End Try
End Sub

Public Sub GetBGMEnabled As Boolean
	Return GetSetting(KEY_BGM_ENABLED, DEFAULT_BGM_ENABLED)
End Sub

Public Sub GetSFXEnabled As Boolean
	Return GetSetting(KEY_SFX_ENABLED, DEFAULT_SFX_ENABLED)
End Sub

'========================================
' UTILITY METHODS
'========================================
Private Sub SettingExists(key As String) As Boolean
	Try
		Dim query As String
		query = "SELECT COUNT(*) as count FROM " & TABLE_NAME & " " & _
		    "WHERE setting_key = ?"
		
		Dim cursor As Cursor = sql.ExecQuery2(query, Array As String(key))
		cursor.Position = 0
		Dim count As Int = cursor.GetInt("count")
		cursor.Close
		
		Return count > 0
	Catch
		Log("Error checking if setting exists: " & LastException.Message)
		Return False
	End Try
End Sub

Public Sub ResetToDefaults
	Try
		SaveBGMEnabled(DEFAULT_BGM_ENABLED)
		SaveSFXEnabled(DEFAULT_SFX_ENABLED)
		Log("Settings reset to defaults")
	Catch
		Log("Error resetting settings: " & LastException.Message)
	End Try
End Sub

Public Sub GetAllSettings As Map
	Dim settingsMap As Map
	settingsMap.Initialize
	
	Try
		settingsMap.Put("bgm_enabled", GetBGMEnabled)
		settingsMap.Put("sfx_enabled", GetSFXEnabled)
	Catch
		Log("Error getting all settings: " & LastException.Message)
	End Try
	
	Return settingsMap
End Sub

'========================================
' DATABASE MAINTENANCE
'========================================
Public Sub ClearAllSettings
	Try
		Dim query As String = "DELETE FROM " & TABLE_NAME
		sql.ExecNonQuery(query)
		InitializeDefaultSettings
		Log("All settings cleared and reset to defaults")
	Catch
		Log("Error clearing settings: " & LastException.Message)
	End Try
End Sub

Public Sub Close
	Try
		If sql.IsInitialized Then
			sql.Close
			Log("Settings database connection closed")
		End If
	Catch
		Log("Error closing settings database: " & LastException.Message)
	End Try
End Sub

Public Sub GetDatabaseInfo As String
	Try
		Dim info As String = "Settings Database Information:" & CRLF
		info = info & "Database: " & DB_NAME & CRLF
		info = info & "BGM Enabled: " & GetBGMEnabled & CRLF
		info = info & "SFX Enabled: " & GetSFXEnabled & CRLF
		Return info
	Catch
		Return "Error retrieving database info"
	End Try
End Sub
