package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class snake extends Activity implements B4AActivity{
	public static snake mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.snake");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (snake).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.snake");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.snake", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (snake) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (snake) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return snake.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (snake) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (snake) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            snake mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (snake) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static b4a.example.soundmanager _soundmgr = null;
public static anywheresoftware.b4a.objects.Timer _gamelooptimer = null;
public static int _frame_rate_ms = 0;
public anywheresoftware.b4a.objects.PanelWrapper _pnlgameboard = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblscore = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbllevel = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblhighscore = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblpause = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnup = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btndown = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnleft = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnright = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnpause = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnrestart = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnmenu = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnleaderboard = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlgameover1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlgameover = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnldialog = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblgameovertitle = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblgameoverscore = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblgameoverlevel = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtplayername = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnsavescore = null;
public b4a.example.snakegamemanager _snakegame = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlsettings = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlsettings1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlsettingsdialog = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsettingstitle = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblbgmvolume = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsfxvolume = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnsavesettings = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper _bgmtoggle = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper _sfxtoggle = null;
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 58;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 59;BA.debugLine="Activity.LoadLayout(\"SnakeLayout\")";
mostCurrent._activity.LoadLayout("SnakeLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 61;BA.debugLine="InitializeComponents(FirstTime)";
_initializecomponents(_firsttime);
 //BA.debugLineNum = 62;BA.debugLine="InitializeGameOverDialog";
_initializegameoverdialog();
 //BA.debugLineNum = 63;BA.debugLine="InitializeSettingsDialog";
_initializesettingsdialog();
 //BA.debugLineNum = 64;BA.debugLine="gameLoopTimer.Initialize(\"gameLoopTimer\", FRAME_R";
_gamelooptimer.Initialize(processBA,"gameLoopTimer",(long) (_frame_rate_ms));
 //BA.debugLineNum = 66;BA.debugLine="StartNewGame";
_startnewgame();
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 82;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
 //BA.debugLineNum = 83;BA.debugLine="Try";
try { //BA.debugLineNum = 84;BA.debugLine="If snakeGame.IsInitialized Then snakeGame.Pause";
if (mostCurrent._snakegame.IsInitialized /*boolean*/ ()) { 
mostCurrent._snakegame._pause /*String*/ ();};
 //BA.debugLineNum = 85;BA.debugLine="If gameLoopTimer.IsInitialized Then gameLoopTime";
if (_gamelooptimer.IsInitialized()) { 
_gamelooptimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);};
 //BA.debugLineNum = 86;BA.debugLine="soundmgr.PauseMusic";
_soundmgr._pausemusic /*String*/ ();
 } 
       catch (Exception e6) {
			processBA.setLastException(e6); //BA.debugLineNum = 88;BA.debugLine="Log(\"Error in Activity_Pause: \" & LastException.";
anywheresoftware.b4a.keywords.Common.LogImpl("83538950","Error in Activity_Pause: "+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
 };
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 69;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 70;BA.debugLine="Try";
try { //BA.debugLineNum = 71;BA.debugLine="If snakeGame.IsInitialized And gameLoopTimer.IsI";
if (mostCurrent._snakegame.IsInitialized /*boolean*/ () && _gamelooptimer.IsInitialized()) { 
 //BA.debugLineNum = 72;BA.debugLine="If snakeGame.IsActive And Not(snakeGame.IsPause";
if (mostCurrent._snakegame._isactive /*boolean*/  && anywheresoftware.b4a.keywords.Common.Not(mostCurrent._snakegame._ispausedstate /*boolean*/ )) { 
 //BA.debugLineNum = 73;BA.debugLine="gameLoopTimer.Enabled = True";
_gamelooptimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 74;BA.debugLine="soundmgr.ResumeMusic";
_soundmgr._resumemusic /*String*/ ();
 };
 };
 } 
       catch (Exception e9) {
			processBA.setLastException(e9); //BA.debugLineNum = 78;BA.debugLine="Log(\"Error in Activity_Resume: \" & LastException";
anywheresoftware.b4a.keywords.Common.LogImpl("83473417","Error in Activity_Resume: "+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
 };
 //BA.debugLineNum = 80;BA.debugLine="End Sub";
return "";
}
public static String  _btndown_click() throws Exception{
 //BA.debugLineNum = 161;BA.debugLine="Sub btnDown_Click";
 //BA.debugLineNum = 162;BA.debugLine="snakeGame.SetDirection(\"DOWN\")";
mostCurrent._snakegame._setdirection /*String*/ ("DOWN");
 //BA.debugLineNum = 163;BA.debugLine="End Sub";
return "";
}
public static String  _btnleaderboard_click() throws Exception{
 //BA.debugLineNum = 198;BA.debugLine="Sub btnLeaderboard_Click";
 //BA.debugLineNum = 199;BA.debugLine="snakeGame.Pause";
mostCurrent._snakegame._pause /*String*/ ();
 //BA.debugLineNum = 200;BA.debugLine="gameLoopTimer.Enabled = False";
_gamelooptimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 201;BA.debugLine="soundmgr.PauseMusic";
_soundmgr._pausemusic /*String*/ ();
 //BA.debugLineNum = 202;BA.debugLine="StartActivity(LB)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._lb.getObject()));
 //BA.debugLineNum = 203;BA.debugLine="End Sub";
return "";
}
public static String  _btnleft_click() throws Exception{
 //BA.debugLineNum = 165;BA.debugLine="Sub btnLeft_Click";
 //BA.debugLineNum = 166;BA.debugLine="snakeGame.SetDirection(\"LEFT\")";
mostCurrent._snakegame._setdirection /*String*/ ("LEFT");
 //BA.debugLineNum = 167;BA.debugLine="End Sub";
return "";
}
public static String  _btnmenu_click() throws Exception{
 //BA.debugLineNum = 191;BA.debugLine="Sub btnMenu_Click";
 //BA.debugLineNum = 192;BA.debugLine="snakeGame.Pause";
mostCurrent._snakegame._pause /*String*/ ();
 //BA.debugLineNum = 193;BA.debugLine="gameLoopTimer.Enabled = False";
_gamelooptimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 194;BA.debugLine="soundmgr.PauseMusic";
_soundmgr._pausemusic /*String*/ ();
 //BA.debugLineNum = 195;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
public static String  _btnpause_click() throws Exception{
 //BA.debugLineNum = 173;BA.debugLine="Sub btnPause_Click";
 //BA.debugLineNum = 174;BA.debugLine="If snakeGame.IsPausedState Then";
if (mostCurrent._snakegame._ispausedstate /*boolean*/ ) { 
 //BA.debugLineNum = 175;BA.debugLine="snakeGame.Resume";
mostCurrent._snakegame._resume /*String*/ ();
 //BA.debugLineNum = 176;BA.debugLine="soundmgr.ResumeMusic";
_soundmgr._resumemusic /*String*/ ();
 //BA.debugLineNum = 177;BA.debugLine="lblPause.Text = \"Pause\"";
mostCurrent._lblpause.setText(BA.ObjectToCharSequence("Pause"));
 //BA.debugLineNum = 178;BA.debugLine="gameLoopTimer.Enabled = True";
_gamelooptimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 180;BA.debugLine="snakeGame.Pause";
mostCurrent._snakegame._pause /*String*/ ();
 //BA.debugLineNum = 181;BA.debugLine="soundmgr.PauseMusic";
_soundmgr._pausemusic /*String*/ ();
 //BA.debugLineNum = 182;BA.debugLine="lblPause.Text = \"Resume\"";
mostCurrent._lblpause.setText(BA.ObjectToCharSequence("Resume"));
 //BA.debugLineNum = 183;BA.debugLine="gameLoopTimer.Enabled = False";
_gamelooptimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 185;BA.debugLine="End Sub";
return "";
}
public static String  _btnrestart_click() throws Exception{
 //BA.debugLineNum = 187;BA.debugLine="Sub btnRestart_Click";
 //BA.debugLineNum = 188;BA.debugLine="StartNewGame";
_startnewgame();
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}
public static String  _btnright_click() throws Exception{
 //BA.debugLineNum = 169;BA.debugLine="Sub btnRight_Click";
 //BA.debugLineNum = 170;BA.debugLine="snakeGame.SetDirection(\"RIGHT\")";
mostCurrent._snakegame._setdirection /*String*/ ("RIGHT");
 //BA.debugLineNum = 171;BA.debugLine="End Sub";
return "";
}
public static String  _btnsavescore_click() throws Exception{
String[] _scoredata = null;
int _score = 0;
 //BA.debugLineNum = 244;BA.debugLine="Sub btnSaveScore_Click";
 //BA.debugLineNum = 245;BA.debugLine="Dim scoreData() As String = Regex.Split(\",\", btnS";
_scoredata = anywheresoftware.b4a.keywords.Common.Regex.Split(",",BA.ObjectToString(mostCurrent._btnsavescore.getTag()));
 //BA.debugLineNum = 246;BA.debugLine="Dim score As Int = scoreData(0)";
_score = (int)(Double.parseDouble(_scoredata[(int) (0)]));
 //BA.debugLineNum = 248;BA.debugLine="SavePlayerScore(score)";
_saveplayerscore(_score);
 //BA.debugLineNum = 249;BA.debugLine="End Sub";
return "";
}
public static String  _btnsavesettings_click() throws Exception{
 //BA.debugLineNum = 286;BA.debugLine="Private Sub btnSaveSettings_Click";
 //BA.debugLineNum = 288;BA.debugLine="soundmgr.SetBGMEnabled(bgmToggle.Checked)";
_soundmgr._setbgmenabled /*String*/ (mostCurrent._bgmtoggle.getChecked());
 //BA.debugLineNum = 289;BA.debugLine="soundmgr.SetSFXEnabled(sfxToggle.Checked)";
_soundmgr._setsfxenabled /*String*/ (mostCurrent._sfxtoggle.getChecked());
 //BA.debugLineNum = 290;BA.debugLine="pnlSettings1.Visible = False";
mostCurrent._pnlsettings1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 291;BA.debugLine="End Sub";
return "";
}
public static String  _btnsettings_click() throws Exception{
 //BA.debugLineNum = 272;BA.debugLine="Private Sub btnSettings_Click";
 //BA.debugLineNum = 273;BA.debugLine="snakeGame.Pause";
mostCurrent._snakegame._pause /*String*/ ();
 //BA.debugLineNum = 274;BA.debugLine="lblPause.Text = \"Resume\"";
mostCurrent._lblpause.setText(BA.ObjectToCharSequence("Resume"));
 //BA.debugLineNum = 275;BA.debugLine="soundmgr.PauseMusic";
_soundmgr._pausemusic /*String*/ ();
 //BA.debugLineNum = 276;BA.debugLine="gameLoopTimer.Enabled = False";
_gamelooptimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 279;BA.debugLine="bgmToggle.Checked = soundmgr.GetBGMEnabled";
mostCurrent._bgmtoggle.setChecked(_soundmgr._getbgmenabled /*boolean*/ ());
 //BA.debugLineNum = 280;BA.debugLine="sfxToggle.Checked = soundmgr.GetSFXEnabled";
mostCurrent._sfxtoggle.setChecked(_soundmgr._getsfxenabled /*boolean*/ ());
 //BA.debugLineNum = 282;BA.debugLine="pnlSettings1.Visible = True";
mostCurrent._pnlsettings1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 283;BA.debugLine="pnlSettings1.BringToFront";
mostCurrent._pnlsettings1.BringToFront();
 //BA.debugLineNum = 284;BA.debugLine="End Sub";
return "";
}
public static String  _btnup_click() throws Exception{
 //BA.debugLineNum = 157;BA.debugLine="Sub btnUp_Click";
 //BA.debugLineNum = 158;BA.debugLine="snakeGame.SetDirection(\"UP\")";
mostCurrent._snakegame._setdirection /*String*/ ("UP");
 //BA.debugLineNum = 159;BA.debugLine="End Sub";
return "";
}
public static String  _gamelooptimer_tick() throws Exception{
 //BA.debugLineNum = 129;BA.debugLine="Sub gameLoopTimer_Tick";
 //BA.debugLineNum = 130;BA.debugLine="snakeGame.Update";
mostCurrent._snakegame._update /*String*/ ();
 //BA.debugLineNum = 131;BA.debugLine="RefreshDisplay";
_refreshdisplay();
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 17;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private pnlGameBoard As Panel";
mostCurrent._pnlgameboard = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private lblScore As Label";
mostCurrent._lblscore = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private lblLevel As Label";
mostCurrent._lbllevel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private lblHighScore As Label";
mostCurrent._lblhighscore = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private lblPause As Label";
mostCurrent._lblpause = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private btnUp As Button";
mostCurrent._btnup = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private btnDown As Button";
mostCurrent._btndown = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private btnLeft As Button";
mostCurrent._btnleft = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private btnRight As Button";
mostCurrent._btnright = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private btnPause As Button";
mostCurrent._btnpause = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private btnRestart As Button";
mostCurrent._btnrestart = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private btnMenu As Button";
mostCurrent._btnmenu = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private btnLeaderboard As Button";
mostCurrent._btnleaderboard = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private pnlGameOver1 As Panel";
mostCurrent._pnlgameover1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private pnlGameOver As Panel";
mostCurrent._pnlgameover = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private pnlDialog As Panel";
mostCurrent._pnldialog = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private lblGameOverTitle As Label";
mostCurrent._lblgameovertitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private lblGameOverScore As Label";
mostCurrent._lblgameoverscore = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private lblGameOverLevel As Label";
mostCurrent._lblgameoverlevel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private txtPlayerName As EditText";
mostCurrent._txtplayername = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private btnSaveScore As Button";
mostCurrent._btnsavescore = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private snakeGame As SnakeGameManager";
mostCurrent._snakegame = new b4a.example.snakegamemanager();
 //BA.debugLineNum = 47;BA.debugLine="Private pnlSettings As Panel";
mostCurrent._pnlsettings = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private pnlSettings1 As Panel";
mostCurrent._pnlsettings1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private pnlSettingsDialog As Panel";
mostCurrent._pnlsettingsdialog = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private lblSettingsTitle As Label";
mostCurrent._lblsettingstitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private lblBGMVolume As Label";
mostCurrent._lblbgmvolume = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private lblSFXVolume As Label";
mostCurrent._lblsfxvolume = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private btnSaveSettings As Button";
mostCurrent._btnsavesettings = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private bgmToggle As ToggleButton";
mostCurrent._bgmtoggle = new anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private sfxToggle As ToggleButton";
mostCurrent._sfxtoggle = new anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper();
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _handlegameover(Object _gameoverdata) throws Exception{
Object[] _data = null;
int _score = 0;
int _level = 0;
 //BA.debugLineNum = 208;BA.debugLine="Public Sub HandleGameOver(gameOverData As Object)";
 //BA.debugLineNum = 209;BA.debugLine="Try";
try { //BA.debugLineNum = 210;BA.debugLine="gameLoopTimer.Enabled = False";
_gamelooptimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 211;BA.debugLine="soundmgr.StopMusic";
_soundmgr._stopmusic /*String*/ ();
 //BA.debugLineNum = 213;BA.debugLine="Dim data() As Object = gameOverData";
_data = (Object[])(_gameoverdata);
 //BA.debugLineNum = 214;BA.debugLine="Dim score As Int = data(0)";
_score = (int)(BA.ObjectToNumber(_data[(int) (0)]));
 //BA.debugLineNum = 215;BA.debugLine="Dim level As Int = data(1)";
_level = (int)(BA.ObjectToNumber(_data[(int) (1)]));
 //BA.debugLineNum = 217;BA.debugLine="ShowGameOverDialog(score, level)";
_showgameoverdialog(_score,_level);
 } 
       catch (Exception e9) {
			processBA.setLastException(e9); //BA.debugLineNum = 219;BA.debugLine="Log(\"Error in HandleGameOver: \" & LastException.";
anywheresoftware.b4a.keywords.Common.LogImpl("84587531","Error in HandleGameOver: "+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
 };
 //BA.debugLineNum = 221;BA.debugLine="End Sub";
return "";
}
public static String  _hidegameoverdialog() throws Exception{
 //BA.debugLineNum = 240;BA.debugLine="Private Sub HideGameOverDialog";
 //BA.debugLineNum = 241;BA.debugLine="pnlGameOver1.Visible = False";
mostCurrent._pnlgameover1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 242;BA.debugLine="End Sub";
return "";
}
public static String  _initializecomponents(boolean _isfirsttime) throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Private Sub InitializeComponents(isFirstTime As Bo";
 //BA.debugLineNum = 96;BA.debugLine="gameLoopTimer.Initialize(\"gameLoopTimer\", FRAME_R";
_gamelooptimer.Initialize(processBA,"gameLoopTimer",(long) (_frame_rate_ms));
 //BA.debugLineNum = 99;BA.debugLine="Try";
try { //BA.debugLineNum = 100;BA.debugLine="If soundmgr.IsInitialized = False Then";
if (_soundmgr._isinitialized /*boolean*/ ()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 101;BA.debugLine="soundmgr.Initialize";
_soundmgr._initialize /*String*/ (processBA);
 //BA.debugLineNum = 102;BA.debugLine="soundmgr.LoadAudio";
_soundmgr._loadaudio /*String*/ ();
 };
 } 
       catch (Exception e8) {
			processBA.setLastException(e8); //BA.debugLineNum = 105;BA.debugLine="soundmgr.Initialize";
_soundmgr._initialize /*String*/ (processBA);
 //BA.debugLineNum = 106;BA.debugLine="soundmgr.LoadAudio";
_soundmgr._loadaudio /*String*/ ();
 };
 //BA.debugLineNum = 109;BA.debugLine="snakeGame.Initialize(pnlGameBoard)";
mostCurrent._snakegame._initialize /*String*/ (mostCurrent.activityBA,mostCurrent._pnlgameboard);
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _initializegameoverdialog() throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Private Sub InitializeGameOverDialog";
 //BA.debugLineNum = 113;BA.debugLine="pnlGameOver1.Initialize(\"\")";
mostCurrent._pnlgameover1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 114;BA.debugLine="Activity.AddView(pnlGameOver1, 0, 0, 100%x, 100%y";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pnlgameover1.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 115;BA.debugLine="pnlGameOver1.LoadLayout(\"GameOverDialog\")";
mostCurrent._pnlgameover1.LoadLayout("GameOverDialog",mostCurrent.activityBA);
 //BA.debugLineNum = 116;BA.debugLine="pnlGameOver1.Visible = False";
mostCurrent._pnlgameover1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _initializesettingsdialog() throws Exception{
 //BA.debugLineNum = 119;BA.debugLine="Private Sub InitializeSettingsDialog";
 //BA.debugLineNum = 120;BA.debugLine="pnlSettings1.Initialize(\"\")";
mostCurrent._pnlsettings1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 121;BA.debugLine="Activity.AddView(pnlSettings1, 0, 0, 100%x, 100%y";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pnlsettings1.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 122;BA.debugLine="pnlSettings1.LoadLayout(\"SettingsDialog\")";
mostCurrent._pnlsettings1.LoadLayout("SettingsDialog",mostCurrent.activityBA);
 //BA.debugLineNum = 123;BA.debugLine="pnlSettings1.Visible = False";
mostCurrent._pnlsettings1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 124;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 12;BA.debugLine="Private soundmgr As SoundManager";
_soundmgr = new b4a.example.soundmanager();
 //BA.debugLineNum = 13;BA.debugLine="Private gameLoopTimer As Timer";
_gamelooptimer = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 14;BA.debugLine="Private Const FRAME_RATE_MS As Int = 300  ' Snake";
_frame_rate_ms = (int) (300);
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public static String  _refreshdisplay() throws Exception{
 //BA.debugLineNum = 134;BA.debugLine="Private Sub RefreshDisplay";
 //BA.debugLineNum = 135;BA.debugLine="snakeGame.Draw";
mostCurrent._snakegame._draw /*String*/ ();
 //BA.debugLineNum = 136;BA.debugLine="UpdateStatistics";
_updatestatistics();
 //BA.debugLineNum = 137;BA.debugLine="End Sub";
return "";
}
public static void  _saveplayerscore(int _score) throws Exception{
ResumableSub_SavePlayerScore rsub = new ResumableSub_SavePlayerScore(null,_score);
rsub.resume(processBA, null);
}
public static class ResumableSub_SavePlayerScore extends BA.ResumableSub {
public ResumableSub_SavePlayerScore(b4a.example.snake parent,int _score) {
this.parent = parent;
this._score = _score;
}
b4a.example.snake parent;
int _score;
String _playername = "";
boolean _success = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 252;BA.debugLine="Dim playerName As String = txtPlayerName.Text.Tri";
_playername = parent.mostCurrent._txtplayername.getText().trim();
 //BA.debugLineNum = 253;BA.debugLine="If playerName.Length = 0 Then playerName = \"Anony";
if (true) break;

case 1:
//if
this.state = 6;
if (_playername.length()==0) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
_playername = "Anonymous";
if (true) break;

case 6:
//C
this.state = 7;
;
 //BA.debugLineNum = 254;BA.debugLine="If playerName.Length > 20 Then playerName = playe";
if (true) break;

case 7:
//if
this.state = 12;
if (_playername.length()>20) { 
this.state = 9;
;}if (true) break;

case 9:
//C
this.state = 12;
_playername = _playername.substring((int) (0),(int) (20));
if (true) break;

case 12:
//C
this.state = 13;
;
 //BA.debugLineNum = 256;BA.debugLine="Try";
if (true) break;

case 13:
//try
this.state = 24;
this.catchState = 23;
this.state = 15;
if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 23;
 //BA.debugLineNum = 257;BA.debugLine="Dim success As Boolean = Starter.leaderboardMana";
_success = parent.mostCurrent._starter._leaderboardmanager /*b4a.example.lbmanager*/ ._addscore /*boolean*/ (_playername,_score);
 //BA.debugLineNum = 259;BA.debugLine="If success Then";
if (true) break;

case 16:
//if
this.state = 21;
if (_success) { 
this.state = 18;
}else {
this.state = 20;
}if (true) break;

case 18:
//C
this.state = 21;
 //BA.debugLineNum = 260;BA.debugLine="btnSaveScore.Enabled = False";
parent.mostCurrent._btnsavescore.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 261;BA.debugLine="btnSaveScore.Text = \"Score Saved ✓\"";
parent.mostCurrent._btnsavescore.setText(BA.ObjectToCharSequence("Score Saved ✓"));
 //BA.debugLineNum = 262;BA.debugLine="Sleep(1000)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (1000));
this.state = 25;
return;
case 25:
//C
this.state = 21;
;
 //BA.debugLineNum = 263;BA.debugLine="HideGameOverDialog";
_hidegameoverdialog();
 if (true) break;

case 20:
//C
this.state = 21;
 //BA.debugLineNum = 265;BA.debugLine="ToastMessageShow(\"Error saving score\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Error saving score"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 21:
//C
this.state = 24;
;
 if (true) break;

case 23:
//C
this.state = 24;
this.catchState = 0;
 //BA.debugLineNum = 268;BA.debugLine="ToastMessageShow(\"Error saving score\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Error saving score"),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;
if (true) break;

case 24:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 270;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static String  _showgameoverdialog(int _score,int _level) throws Exception{
 //BA.debugLineNum = 223;BA.debugLine="Private Sub ShowGameOverDialog(score As Int, level";
 //BA.debugLineNum = 224;BA.debugLine="lblGameOverTitle.Text = \"GAME OVER!\"";
mostCurrent._lblgameovertitle.setText(BA.ObjectToCharSequence("GAME OVER!"));
 //BA.debugLineNum = 225;BA.debugLine="lblGameOverScore.Text = \"Score: \" & score";
mostCurrent._lblgameoverscore.setText(BA.ObjectToCharSequence("Score: "+BA.NumberToString(_score)));
 //BA.debugLineNum = 226;BA.debugLine="lblGameOverLevel.Text = \"Level: \" & level";
mostCurrent._lblgameoverlevel.setText(BA.ObjectToCharSequence("Level: "+BA.NumberToString(_level)));
 //BA.debugLineNum = 228;BA.debugLine="txtPlayerName.Text = \"Player\"";
mostCurrent._txtplayername.setText(BA.ObjectToCharSequence("Player"));
 //BA.debugLineNum = 229;BA.debugLine="txtPlayerName.SelectAll";
mostCurrent._txtplayername.SelectAll();
 //BA.debugLineNum = 231;BA.debugLine="pnlGameOver1.Visible = True";
mostCurrent._pnlgameover1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 232;BA.debugLine="pnlGameOver1.BringToFront";
mostCurrent._pnlgameover1.BringToFront();
 //BA.debugLineNum = 233;BA.debugLine="txtPlayerName.RequestFocus";
mostCurrent._txtplayername.RequestFocus();
 //BA.debugLineNum = 235;BA.debugLine="btnSaveScore.Enabled = True";
mostCurrent._btnsavescore.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 236;BA.debugLine="btnSaveScore.Text = \"Save Score\"";
mostCurrent._btnsavescore.setText(BA.ObjectToCharSequence("Save Score"));
 //BA.debugLineNum = 237;BA.debugLine="btnSaveScore.Tag = score & \",\" & level & \",0\"";
mostCurrent._btnsavescore.setTag((Object)(BA.NumberToString(_score)+","+BA.NumberToString(_level)+",0"));
 //BA.debugLineNum = 238;BA.debugLine="End Sub";
return "";
}
public static String  _startnewgame() throws Exception{
 //BA.debugLineNum = 148;BA.debugLine="Private Sub StartNewGame";
 //BA.debugLineNum = 149;BA.debugLine="snakeGame.StartNewGame";
mostCurrent._snakegame._startnewgame /*String*/ ();
 //BA.debugLineNum = 150;BA.debugLine="gameLoopTimer.Enabled = True";
_gamelooptimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 151;BA.debugLine="lblPause.Text = \"Pause\"";
mostCurrent._lblpause.setText(BA.ObjectToCharSequence("Pause"));
 //BA.debugLineNum = 152;BA.debugLine="End Sub";
return "";
}
public static String  _updatestatistics() throws Exception{
 //BA.debugLineNum = 139;BA.debugLine="Private Sub UpdateStatistics";
 //BA.debugLineNum = 140;BA.debugLine="lblScore.Text = \"Score: \" & CRLF & snakeGame.GetS";
mostCurrent._lblscore.setText(BA.ObjectToCharSequence("Score: "+anywheresoftware.b4a.keywords.Common.CRLF+BA.NumberToString(mostCurrent._snakegame._getscore /*int*/ ())));
 //BA.debugLineNum = 141;BA.debugLine="lblLevel.Text = \"Level: \" & CRLF & snakeGame.GetL";
mostCurrent._lbllevel.setText(BA.ObjectToCharSequence("Level: "+anywheresoftware.b4a.keywords.Common.CRLF+BA.NumberToString(mostCurrent._snakegame._getlevel /*int*/ ())));
 //BA.debugLineNum = 142;BA.debugLine="lblHighScore.Text = \"High: \" & CRLF & snakeGame.G";
mostCurrent._lblhighscore.setText(BA.ObjectToCharSequence("High: "+anywheresoftware.b4a.keywords.Common.CRLF+BA.NumberToString(mostCurrent._snakegame._gethighscore /*int*/ ())));
 //BA.debugLineNum = 143;BA.debugLine="End Sub";
return "";
}
}
