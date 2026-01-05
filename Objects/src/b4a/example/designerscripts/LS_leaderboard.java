package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_leaderboard{

public static void LS_general(anywheresoftware.b4a.BA ba, android.view.View parent, anywheresoftware.b4a.keywords.LayoutValues lv, java.util.Map props,
java.util.Map<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) throws Exception {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[Leaderboard/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 4;BA.debugLine="btnBack.Width = 30%x"[Leaderboard/General script]
views.get("btnback").vw.setWidth((int)((30d / 100 * width)));
//BA.debugLineNum = 5;BA.debugLine="btnBack.Bottom = 75%y"[Leaderboard/General script]
views.get("btnback").vw.setTop((int)((75d / 100 * height) - (views.get("btnback").vw.getHeight())));
//BA.debugLineNum = 6;BA.debugLine="btnClearScores.Width = 30%x"[Leaderboard/General script]
views.get("btnclearscores").vw.setWidth((int)((30d / 100 * width)));
//BA.debugLineNum = 7;BA.debugLine="btnClearScores.Bottom = 75%y"[Leaderboard/General script]
views.get("btnclearscores").vw.setTop((int)((75d / 100 * height) - (views.get("btnclearscores").vw.getHeight())));
//BA.debugLineNum = 8;BA.debugLine="btnClearScores.Right = 80%x"[Leaderboard/General script]
views.get("btnclearscores").vw.setLeft((int)((80d / 100 * width) - (views.get("btnclearscores").vw.getWidth())));

}
}