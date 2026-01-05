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

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
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
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
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
public anywheresoftware.b4a.objects.PanelWrapper _pnlgameboard = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnltetris = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltetris = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlsnake = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsnake = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlspaceinvaders = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblspaceinvaders = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnup = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btndown = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnbdrop = null;
public static int _selectedindex = 0;
public anywheresoftware.b4a.objects.PanelWrapper[] _gameoptions = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (tetris.mostCurrent != null);
vis = vis | (snake.mostCurrent != null);
vis = vis | (spaceinvaders.mostCurrent != null);
vis = vis | (lb.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 45;BA.debugLine="Activity.LoadLayout(\"MainLayout\")";
mostCurrent._activity.LoadLayout("MainLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 46;BA.debugLine="InitializeMenu";
_initializemenu();
 //BA.debugLineNum = 47;BA.debugLine="UpdateSelection";
_updateselection();
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 53;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public static String  _applyselectionstyle(anywheresoftware.b4a.objects.PanelWrapper _panel,boolean _isselected) throws Exception{
 //BA.debugLineNum = 86;BA.debugLine="Private Sub ApplySelectionStyle(panel As Panel, is";
 //BA.debugLineNum = 87;BA.debugLine="If isSelected Then";
if (_isselected) { 
 //BA.debugLineNum = 89;BA.debugLine="panel.SetLayoutAnimated(150, panel.Left, panel.T";
_panel.SetLayoutAnimated((int) (150),_panel.getLeft(),_panel.getTop(),_panel.getWidth(),_panel.getHeight());
 //BA.debugLineNum = 90;BA.debugLine="panel.Color = Colors.RGB(30,30,30)";
_panel.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (30),(int) (30),(int) (30)));
 }else {
 //BA.debugLineNum = 92;BA.debugLine="panel.Color = Colors.RGB(40,40,40)";
_panel.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (40),(int) (40),(int) (40)));
 };
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return "";
}
public static String  _btnbdrop_click() throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Private Sub btnBdrop_Click";
 //BA.debugLineNum = 113;BA.debugLine="SelectCurrentGame";
_selectcurrentgame();
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static String  _btndown_click() throws Exception{
 //BA.debugLineNum = 103;BA.debugLine="Sub btnDown_Click";
 //BA.debugLineNum = 104;BA.debugLine="MoveSelectionDown";
_moveselectiondown();
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public static String  _btnselect_click() throws Exception{
 //BA.debugLineNum = 107;BA.debugLine="Sub btnSelect_Click";
 //BA.debugLineNum = 108;BA.debugLine="SelectCurrentGame";
_selectcurrentgame();
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return "";
}
public static String  _btnup_click() throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub btnUp_Click";
 //BA.debugLineNum = 100;BA.debugLine="MoveSelectionUp";
_moveselectionup();
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Private pnlGameBoard As Panel";
mostCurrent._pnlgameboard = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private pnlTetris As Panel";
mostCurrent._pnltetris = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private lblTetris As Label";
mostCurrent._lbltetris = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private pnlSnake As Panel";
mostCurrent._pnlsnake = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private lblSnake As Label";
mostCurrent._lblsnake = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private pnlSpaceInvaders As Panel";
mostCurrent._pnlspaceinvaders = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private lblSpaceInvaders As Label";
mostCurrent._lblspaceinvaders = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private btnUp As Button";
mostCurrent._btnup = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private btnDown As Button";
mostCurrent._btndown = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private btnBdrop As Button";
mostCurrent._btnbdrop = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private selectedIndex As Int = 0";
_selectedindex = (int) (0);
 //BA.debugLineNum = 41;BA.debugLine="Private gameOptions() As Panel";
mostCurrent._gameoptions = new anywheresoftware.b4a.objects.PanelWrapper[(int) (0)];
{
int d0 = mostCurrent._gameoptions.length;
for (int i0 = 0;i0 < d0;i0++) {
mostCurrent._gameoptions[i0] = new anywheresoftware.b4a.objects.PanelWrapper();
}
}
;
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public static String  _initializemenu() throws Exception{
 //BA.debugLineNum = 59;BA.debugLine="Private Sub InitializeMenu";
 //BA.debugLineNum = 61;BA.debugLine="gameOptions = Array As Panel(pnlTetris, pnlSnake,";
mostCurrent._gameoptions = new anywheresoftware.b4a.objects.PanelWrapper[]{mostCurrent._pnltetris,mostCurrent._pnlsnake,mostCurrent._pnlspaceinvaders};
 //BA.debugLineNum = 64;BA.debugLine="selectedIndex = 0";
_selectedindex = (int) (0);
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static String  _moveselectiondown() throws Exception{
 //BA.debugLineNum = 124;BA.debugLine="Private Sub MoveSelectionDown";
 //BA.debugLineNum = 125;BA.debugLine="selectedIndex = selectedIndex + 1";
_selectedindex = (int) (_selectedindex+1);
 //BA.debugLineNum = 126;BA.debugLine="If selectedIndex >= gameOptions.Length Then";
if (_selectedindex>=mostCurrent._gameoptions.length) { 
 //BA.debugLineNum = 127;BA.debugLine="selectedIndex = 0";
_selectedindex = (int) (0);
 };
 //BA.debugLineNum = 129;BA.debugLine="UpdateSelection";
_updateselection();
 //BA.debugLineNum = 130;BA.debugLine="End Sub";
return "";
}
public static String  _moveselectionup() throws Exception{
 //BA.debugLineNum = 116;BA.debugLine="Private Sub MoveSelectionUp";
 //BA.debugLineNum = 117;BA.debugLine="selectedIndex = selectedIndex - 1";
_selectedindex = (int) (_selectedindex-1);
 //BA.debugLineNum = 118;BA.debugLine="If selectedIndex < 0 Then";
if (_selectedindex<0) { 
 //BA.debugLineNum = 119;BA.debugLine="selectedIndex = gameOptions.Length - 1";
_selectedindex = (int) (mostCurrent._gameoptions.length-1);
 };
 //BA.debugLineNum = 121;BA.debugLine="UpdateSelection";
_updateselection();
 //BA.debugLineNum = 122;BA.debugLine="End Sub";
return "";
}
public static String  _pnlsnake_click() throws Exception{
 //BA.debugLineNum = 158;BA.debugLine="Sub pnlSnake_Click";
 //BA.debugLineNum = 159;BA.debugLine="selectedIndex = 1";
_selectedindex = (int) (1);
 //BA.debugLineNum = 160;BA.debugLine="UpdateSelection";
_updateselection();
 //BA.debugLineNum = 161;BA.debugLine="SelectCurrentGame";
_selectcurrentgame();
 //BA.debugLineNum = 162;BA.debugLine="End Sub";
return "";
}
public static String  _pnlspaceinvaders_click() throws Exception{
 //BA.debugLineNum = 164;BA.debugLine="Sub pnlSpaceInvaders_Click";
 //BA.debugLineNum = 165;BA.debugLine="selectedIndex = 2";
_selectedindex = (int) (2);
 //BA.debugLineNum = 166;BA.debugLine="UpdateSelection";
_updateselection();
 //BA.debugLineNum = 167;BA.debugLine="SelectCurrentGame";
_selectcurrentgame();
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return "";
}
public static String  _pnltetris_click() throws Exception{
 //BA.debugLineNum = 152;BA.debugLine="Sub pnlTetris_Click";
 //BA.debugLineNum = 153;BA.debugLine="selectedIndex = 0";
_selectedindex = (int) (0);
 //BA.debugLineNum = 154;BA.debugLine="UpdateSelection";
_updateselection();
 //BA.debugLineNum = 155;BA.debugLine="SelectCurrentGame";
_selectcurrentgame();
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
tetris._process_globals();
snake._process_globals();
spaceinvaders._process_globals();
lb._process_globals();
starter._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _selectcurrentgame() throws Exception{
 //BA.debugLineNum = 132;BA.debugLine="Sub SelectCurrentGame";
 //BA.debugLineNum = 133;BA.debugLine="Select selectedIndex";
switch (_selectedindex) {
case 0: {
 //BA.debugLineNum = 136;BA.debugLine="Starter.leaderboardManager.currentTableName = S";
mostCurrent._starter._leaderboardmanager /*b4a.example.lbmanager*/ ._currenttablename /*String*/  = mostCurrent._starter._leaderboardmanager /*b4a.example.lbmanager*/ ._table_tetris /*String*/ ;
 //BA.debugLineNum = 137;BA.debugLine="StartActivity(Tetris)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._tetris.getObject()));
 break; }
case 1: {
 //BA.debugLineNum = 140;BA.debugLine="Starter.leaderboardManager.currentTableName = S";
mostCurrent._starter._leaderboardmanager /*b4a.example.lbmanager*/ ._currenttablename /*String*/  = mostCurrent._starter._leaderboardmanager /*b4a.example.lbmanager*/ ._table_snake /*String*/ ;
 //BA.debugLineNum = 141;BA.debugLine="StartActivity(Snake)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._snake.getObject()));
 break; }
case 2: {
 //BA.debugLineNum = 144;BA.debugLine="Starter.leaderboardManager.currentTableName = S";
mostCurrent._starter._leaderboardmanager /*b4a.example.lbmanager*/ ._currenttablename /*String*/  = mostCurrent._starter._leaderboardmanager /*b4a.example.lbmanager*/ ._table_space /*String*/ ;
 //BA.debugLineNum = 145;BA.debugLine="StartActivity(SpaceInvaders)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._spaceinvaders.getObject()));
 break; }
}
;
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _updateselection() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
 //BA.debugLineNum = 70;BA.debugLine="Private Sub UpdateSelection";
 //BA.debugLineNum = 72;BA.debugLine="For i = 0 To gameOptions.Length - 1";
{
final int step1 = 1;
final int limit1 = (int) (mostCurrent._gameoptions.length-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 73;BA.debugLine="Dim pnl As Panel = gameOptions(i)";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
_pnl = mostCurrent._gameoptions[_i];
 //BA.debugLineNum = 74;BA.debugLine="If i = selectedIndex Then";
if (_i==_selectedindex) { 
 //BA.debugLineNum = 76;BA.debugLine="pnl.Elevation = 20dip";
_pnl.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))));
 //BA.debugLineNum = 77;BA.debugLine="ApplySelectionStyle(pnl, True)";
_applyselectionstyle(_pnl,anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 80;BA.debugLine="pnl.Elevation = 5dip";
_pnl.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 81;BA.debugLine="ApplySelectionStyle(pnl, False)";
_applyselectionstyle(_pnl,anywheresoftware.b4a.keywords.Common.False);
 };
 }
};
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return "";
}
}
