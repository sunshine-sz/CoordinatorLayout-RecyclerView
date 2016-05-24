# CoordinatorLayout-RecyclerView
可折叠的布局与RecyclerView下拉刷新
##实现可折叠的布局

	在项目对应的build.gradle中添加以下配置
	dependencies {
	    compile 'com.android.support:appcompat-v7:23.1.1'
	    compile 'com.android.support:design:23.1.0'
	}

## CoordinatorLayout

-	CoordinatorLayout实现了多种Material Design中提到的滚动效果，此框架提供几种不需要写java代码就可以实现的动画效果，此控件为以下控件的最大父容器。

#### CollapsingToolbarLayout

-	可折叠的ToolBar容器

		如想使用app开头的属性，需要在布局文件的名控件做以下申明
		xmlns:app="http://schemas.android.com/apk/res-auto"

		部分可用属性说明
		属性1：expandedTitleMarginStart
		使用示例：
		app:expandedTitleMarginStart="14dp"：指定文字和左边缘的间距

		属性2：layout_scrollFlags
		使用示例：
		app:layout_scrollFlags="scroll|exitUntilCollapsed"
		属性值说明：
		scroll:现有容器，可用滚动折叠
		exitUntilCollapsed:可用让ToolBar固定在最顶部，而不伴随收拾的滚动也移出
		enterAlways:ToolBar完全在滚动过程中隐藏，如果手势向下移动则此ToolBar再次显示

		属性3：contentScrim
		使用示例：
		app:contentScrim="#30469b"：折叠后容器的色值

#### ToolBar

-	应用标题 部分属性说明

		属性1：layout_collapseMode
		使用示例：
		app:layout_collapseMode="pin"
		属性值说明
		pin:设置为这个模式时，当CollapsingToolbarLayout完全收缩后，Toolbar还可以停留在屏幕上
		parallax:设置为这个模式时，在内容滚动时，当CollapsingToolbarLayout中的View(比如ImageView)也可以同时滚动，实现视差滚动效果。

		属性2：layout_height
		使用示例：
		android:layout_height="?attr/actionBarSize"
		指定ToolBar收缩后的高度和actionBar保持一致

#### AppBarLayout

-	CollapsingToolbarLayout的父容器，用于管理可折叠ToolBar容器，即CollapsingToolbarLayout伴随手势滚动

#### AppBarLayout伴随底部列表滚动

		1.定义AppBarLayout与滚动视图之间的联系
		2.在RecyclerView或者任意支持嵌套滚动的view,如：NestedScrollView上添加app:layout_behavior，并将其属性值设置为@string/appbar_scrolling_view_behavior
		3.support library包含一个特殊的字符串资源@string/appbar_scrolling_view_behavior它和AppBarLayout.ScrollingViewBehavior相匹配，用来通知AppBarLayout这个特殊的view何时发生了滚动事件，这个behavior需要设置在滚动触发事件view上

