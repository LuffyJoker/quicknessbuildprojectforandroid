package com.peng.commonlib.utils;

/**
 * Created by Mr.Q on 2019/2/16.
 * 描述：
 *      键盘工具类
 */
public class SoftKeyboardUtils {
    /**
     * Android定义了一个属性，名字为windowSoftInputMode, 这个属性用于设置Activity主窗口与软键盘的交互模式
     * 该属性可选的值有两部分，一部分为软键盘的状态控制，控制软键盘是隐藏还是显示，另一部分是Activity窗口的调整，以便腾出空间展示软键盘
     *
     * stateUnspecified-未指定状态：当我们没有设置android:windowSoftInputMode属性的时候，软件默认采用的就是这种交互方式，系统会根据界面采取相应的软键盘的显示模式。
     * stateUnchanged-不改变状态：当前界面的软键盘状态，取决于上一个界面的软键盘状态，无论是隐藏还是显示。
     * stateHidden-隐藏状态：当设置该状态时，软键盘总是被隐藏，不管是否有输入的需求。
     * stateAlwaysHidden-总是隐藏状态：当设置该状态时，软键盘总是被隐藏，和stateHidden不同的是，当我们跳转到下个界面，如果下个页面的软键盘是显示的，而我们再次回来的时候，软键盘就会隐藏起来。
     * stateVisible-可见状态：当设置为这个状态时，软键盘总是可见的，即使在界面上没有输入框的情况下也可以强制弹出来出来。
     * stateAlwaysVisible-总是显示状态：当设置为这个状态时，软键盘总是可见的，和stateVisible不同的是，当我们跳转到下个界面，如果下个页面软键盘是隐藏的，而我们再次回来的时候，软键盘就会显示出来。
     *
     * adjustUnspecified-未指定模式：设置软键盘与软件的显示内容之间的显示关系。当你跟我们没有设置这个值的时候，这个选项也是默认的设置模式。在这中情况下，系统会根据界面选择不同的模式。
     * adjustResize-调整模式：该模式下窗口总是调整屏幕的大小用以保证软键盘的显示空间；这个选项不能和adjustPan同时使用，如果这两个属性都没有被设置，系统会根据窗口中的布局自动选择其中一个。
     * adjustPan-默认模式：该模式下通过不会调整来保证软键盘的空间，而是采取了另外一种策略，系统会通过布局的移动，来保证用户要进行输入的输入框肯定在用户的视野范围里面，从而让用户可以看到自己输入的内容。
     */

    /**
     * 进入Activity后不希望系统自动弹出软键盘
     *
     * 方法一：
     * Android:windowSoftInputMode="adjustUnspecified|stateHidden"
     *
     * 方法二：
     * EditText edit = (EditText) findViewById(R.id.edit); edit.clearFocus();
     *
     * 方法三：
     * EditText edit = (EditText) findViewById(R.id.edit);
     * InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
     * imm.hideSoftInputFromWindow(edit.getWindowToken(),0);
     *
     * 方法四：
     * EditText edit = (EditText) findViewById(R.id.edit);
     * edit.setInputType(InputType.TYPE_NULL);
     */
}
