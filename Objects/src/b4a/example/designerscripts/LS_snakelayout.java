package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_snakelayout{

public static void LS_general(anywheresoftware.b4a.BA ba, android.view.View parent, anywheresoftware.b4a.keywords.LayoutValues lv, java.util.Map props,
java.util.Map<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) throws Exception {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("pnlgameboard").vw.setWidth((int)((300d * scale)));
views.get("pnlgameboard").vw.setHeight((int)((600d * scale)));
views.get("lblscore").vw.setHeight((int)((50d * scale)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblscore").vw).setTextSize((float)(12d));
views.get("lbllevel").vw.setHeight((int)((50d * scale)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lbllevel").vw).setTextSize((float)(12d));
views.get("lblhighscore").vw.setHeight((int)((50d * scale)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblhighscore").vw).setTextSize((float)(12d));
views.get("pnlnextpiece").vw.setHeight((int)((150d * scale)));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblnextpiece").vw).setTextSize((float)(12d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblpause").vw).setTextSize((float)(12d));
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblrestart").vw).setTextSize((float)(12d));
views.get("lblscore").vw.setTop((int)(20d));
views.get("lbllevel").vw.setTop((int)((views.get("lblscore").vw.getTop() + views.get("lblscore").vw.getHeight())));
views.get("lblhighscore").vw.setTop((int)((views.get("lbllevel").vw.getTop() + views.get("lbllevel").vw.getHeight())));
views.get("pnlnextpiece").vw.setTop((int)((views.get("lblhighscore").vw.getTop() + views.get("lblhighscore").vw.getHeight())));
views.get("pnlnextpiece").vw.setHeight((int)((0d / 100 * height)));
views.get("lblpause").vw.setTop((int)((views.get("pnlnextpiece").vw.getTop() + views.get("pnlnextpiece").vw.getHeight())));
views.get("btnpause").vw.setTop((int)((views.get("lblpause").vw.getTop() + views.get("lblpause").vw.getHeight())));
views.get("lblrestart").vw.setTop((int)((views.get("btnpause").vw.getTop() + views.get("btnpause").vw.getHeight())));
views.get("btnrestart").vw.setTop((int)((views.get("lblrestart").vw.getTop() + views.get("lblrestart").vw.getHeight())));
views.get("lblleaderboard").vw.setTop((int)((views.get("btnrestart").vw.getTop() + views.get("btnrestart").vw.getHeight())));
views.get("btnleaderboard").vw.setTop((int)((views.get("lblleaderboard").vw.getTop() + views.get("lblleaderboard").vw.getHeight())));
views.get("lblsettings").vw.setTop((int)((views.get("btnleaderboard").vw.getTop() + views.get("btnleaderboard").vw.getHeight())));
views.get("btnsettings").vw.setTop((int)((views.get("lblsettings").vw.getTop() + views.get("lblsettings").vw.getHeight())));
views.get("pnlgameboard").vw.setTop((int)(20d));
views.get("pnlgameboard").vw.setLeft((int)(20d));
views.get("pnlcontrols").vw.setTop((int)((views.get("pnlgameboard").vw.getTop() + views.get("pnlgameboard").vw.getHeight())+10d));
views.get("pnlcontrols").vw.setLeft((int)(20d));
views.get("label1").vw.setLeft((int)((37d / 100 * width)));
views.get("label1").vw.setTop((int)((11d / 100 * height)));
views.get("btnmenu").vw.setLeft((int)((40d / 100 * width)));
views.get("btnmenu").vw.setTop((int)((15d / 100 * height)));
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}