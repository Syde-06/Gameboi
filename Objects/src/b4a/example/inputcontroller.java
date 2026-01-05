package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class inputcontroller extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.inputcontroller");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.inputcontroller.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public b4a.example.gamemanager _gamemgr = null;
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private gameMgr As GameManager";
_gamemgr = new b4a.example.gamemanager();
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
public String  _handleharddrop() throws Exception{
 //BA.debugLineNum = 36;BA.debugLine="Public Sub HandleHardDrop";
 //BA.debugLineNum = 37;BA.debugLine="gameMgr.ExecuteHardDrop";
_gamemgr._executeharddrop /*String*/ ();
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public String  _handlemoveleft() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Public Sub HandleMoveLeft";
 //BA.debugLineNum = 17;BA.debugLine="gameMgr.MovePieceBy(-1, 0)";
_gamemgr._movepieceby /*boolean*/ ((int) (-1),(int) (0));
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public String  _handlemoveright() throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Public Sub HandleMoveRight";
 //BA.debugLineNum = 21;BA.debugLine="gameMgr.MovePieceBy(1, 0)";
_gamemgr._movepieceby /*boolean*/ ((int) (1),(int) (0));
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public String  _handlerotate() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Public Sub HandleRotate";
 //BA.debugLineNum = 25;BA.debugLine="gameMgr.RotatePiece";
_gamemgr._rotatepiece /*String*/ ();
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return "";
}
public String  _handlesoftdropend() throws Exception{
 //BA.debugLineNum = 32;BA.debugLine="Public Sub HandleSoftDropEnd";
 //BA.debugLineNum = 33;BA.debugLine="gameMgr.SetSoftDrop(False)";
_gamemgr._setsoftdrop /*String*/ (__c.False);
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public String  _handlesoftdropstart() throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Public Sub HandleSoftDropStart";
 //BA.debugLineNum = 29;BA.debugLine="gameMgr.SetSoftDrop(True)";
_gamemgr._setsoftdrop /*String*/ (__c.True);
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,b4a.example.gamemanager _gamemanagerinstance) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 9;BA.debugLine="Public Sub Initialize(gameManagerInstance As GameM";
 //BA.debugLineNum = 10;BA.debugLine="gameMgr = gameManagerInstance";
_gamemgr = _gamemanagerinstance;
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
