B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=13.4
@EndOfDesignText@
'========================================
' SOUND MANAGER CLASS
' Handles background music and sound effects
' Integrated with SettingsManager for toggle persistence
'========================================
Sub Class_Globals
	Private backgroundMusic As MediaPlayer
	Private lineClearSound As MediaPlayer
	Private gamemngr As GameManager
	' Reference to SettingsManager
	Private settingsMgr As SettingsManager
    
	' Fixed volumes when enabled
	Private Const MUSIC_VOLUME As Float = 0.5
	Private Const SFX_VOLUME As Float = 0.8
    
	' Toggle states
	Private isMusicEnabled As Boolean = True
	Private areSoundEffectsEnabled As Boolean = True
	
	' Initialization state
	Private isInitializedFlag As Boolean = False
	Private IsAudioLoadedState As Boolean = False
    
	Private Const MUSIC_FILE As String = "tetris_music.mp3"
	Private Const LINE_CLEAR_FILE As String = "line_clear.mp3"
End Sub

Public Sub Initialize
	If isInitializedFlag Then
		Log("SoundManager already initialized, skipping...")
		Return
	End If
	
	backgroundMusic.Initialize2("backgroundMusic")
	lineClearSound.Initialize2("lineClearSound")
	
	' Initialize SettingsManager
	settingsMgr.Initialize
	
	' Load toggle settings from database
	LoadSettingsFromDatabase
	
	isInitializedFlag = True
	Log("SoundManager initialized successfully")
End Sub

Public Sub IsInitialized As Boolean
	Return isInitializedFlag
End Sub

'========================================
' SETTINGS PERSISTENCE WITH DATABASE
'========================================
Private Sub LoadSettingsFromDatabase
	Try
		' Get toggle settings from database
		isMusicEnabled = settingsMgr.GetBGMEnabled
		areSoundEffectsEnabled = settingsMgr.GetSFXEnabled
		
		Log("Sound settings loaded - Music enabled: " & isMusicEnabled & ", SFX enabled: " & areSoundEffectsEnabled)
	Catch
		Log("Error loading settings from database: " & LastException.Message)
		' Use default values on error
		isMusicEnabled = True
		areSoundEffectsEnabled = True
	End Try
End Sub

Public Sub SaveSettingsToDatabase
	Try
		' Save current toggle settings to database
		settingsMgr.SaveSoundSettings(isMusicEnabled, areSoundEffectsEnabled)
		Log("Sound settings saved - Music enabled: " & isMusicEnabled & ", SFX enabled: " & areSoundEffectsEnabled)
	Catch
		Log("Error saving settings to database: " & LastException.Message)
	End Try
End Sub

'========================================
' AUDIO LOADING
'========================================
Public Sub LoadAudio
	If IsAudioLoaded Then
		Log("Audio already loaded, skipping...")
		Return
	End If
	
	Try
		backgroundMusic.Load(File.DirAssets, MUSIC_FILE)
		backgroundMusic.Looping = True
		backgroundMusic.SetVolume(MUSIC_VOLUME, MUSIC_VOLUME)
        
		lineClearSound.Load(File.DirAssets, LINE_CLEAR_FILE)
		lineClearSound.Looping = False
		lineClearSound.SetVolume(SFX_VOLUME, SFX_VOLUME)
        
		IsAudioLoadedState = True
		Log("Audio loaded successfully")
	Catch
		Log("Error loading audio: " & LastException.Message)
	End Try
End Sub

'========================================
' MUSIC CONTROL
'========================================
Public Sub StartMusic
	If Not(isMusicEnabled) Then Return
    
	Try
		If Not(backgroundMusic.IsPlaying) Then
			' Only reload if not already loaded
			If Not(IsAudioLoadedState) Then
				Try
					backgroundMusic.Load(File.DirAssets, MUSIC_FILE)
					backgroundMusic.Looping = True
					backgroundMusic.SetVolume(MUSIC_VOLUME, MUSIC_VOLUME)
					IsAudioLoadedState = True
				Catch
					' Already loaded, continue
				End Try
			End If
			backgroundMusic.Play
		End If
	Catch
		Log("Error starting music: " & LastException.Message)
	End Try
End Sub

Public Sub StopMusic
	Try
		If backgroundMusic.IsPlaying Then
			backgroundMusic.Stop
		End If
	Catch
		Log("Error stopping music: " & LastException.Message)
	End Try
End Sub

Public Sub PauseMusic
	Try
		If backgroundMusic.IsPlaying Then
			backgroundMusic.Pause
		End If
	Catch
		Log("Error pausing music: " & LastException.Message)
	End Try
End Sub

Public Sub ResumeMusic
	If Not(isMusicEnabled) Then Return
    
	Try
		If Not(backgroundMusic.IsPlaying) Then
			backgroundMusic.Play
		End If
	Catch
		Log("Error resuming music: " & LastException.Message)
	End Try
End Sub

'========================================
' TOGGLE CONTROL METHODS
'========================================
Public Sub SetBGMEnabled(enabled As Boolean)
	isMusicEnabled = enabled
	SaveSettingsToDatabase
End Sub

Public Sub SetSFXEnabled(enabled As Boolean)
	areSoundEffectsEnabled = enabled
	SaveSettingsToDatabase
End Sub

Public Sub GetBGMEnabled As Boolean
	Return isMusicEnabled
End Sub

Public Sub GetSFXEnabled As Boolean
	Return areSoundEffectsEnabled
End Sub

Public Sub ApplySettingsImmediately
	Try
		' Apply music toggle
		If isMusicEnabled Then
			StartMusic
		Else
			StopMusic
		End If
		
		' Save to database
		SaveSettingsToDatabase
	Catch
		Log("Error applying settings: " & LastException.Message)
	End Try
End Sub

'========================================
' SOUND EFFECTS
'========================================
Public Sub PlayLineClearSound
	If Not(areSoundEffectsEnabled) Then Return
    
	Try
		If lineClearSound.IsPlaying Then
			lineClearSound.Stop
		End If
        
		lineClearSound.Position = 0
		lineClearSound.Play
	Catch
		Log("Error playing line clear sound: " & LastException.Message)
	End Try
End Sub

'========================================
' CLEANUP
'========================================
Public Sub Release
	Try
		' Stop all audio first
		StopMusic
		
		' Release media player
		If backgroundMusic.IsInitialized Then
			backgroundMusic.Release
		End If
		
		' Release sound pool
		If lineClearSound.IsInitialized Then
			lineClearSound.Release
		End If
		
		Log("SoundManager released")
	Catch
		Log("Error releasing SoundManager: " & LastException.Message)
	End Try
End Sub

Public Sub IsAudioLoaded As Boolean
	Return backgroundMusic.IsInitialized And lineClearSound.IsInitialized
End Sub

'========================================
' EVENT HANDLERS
'========================================
Sub backgroundMusic_Complete
	If isMusicEnabled Then
		Try
			backgroundMusic.Play
		Catch
			Log("Error restarting music: " & LastException.Message)
		End Try
	End If
End Sub

Sub lineClearSound_Complete
	Try
		lineClearSound.Position = 0
	Catch
		Log("Error resetting sound: " & LastException.Message)
	End Try
End Sub