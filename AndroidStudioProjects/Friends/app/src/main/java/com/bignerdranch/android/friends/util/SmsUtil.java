package com.bignerdranch.android.friends.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;

import com.bignerdranch.android.friends.app.MyApplication;
import com.bignerdranch.android.friends.bean.EmoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用来短信中的表情工具类
 */
public class SmsUtil {

    public static List<EmoBean> emos = new ArrayList<EmoBean>();
    static {
        emos.add(new EmoBean("[emo]ue001"));
        emos.add(new EmoBean("[emo]ue002"));
        emos.add(new EmoBean("[emo]ue003"));
        emos.add(new EmoBean("[emo]ue004"));
        emos.add(new EmoBean("[emo]ue005"));
        emos.add(new EmoBean("[emo]ue006"));
        emos.add(new EmoBean("[emo]ue007"));
        emos.add(new EmoBean("[emo]ue008"));
        emos.add(new EmoBean("[emo]ue009"));
        emos.add(new EmoBean("[emo]ue010"));
        emos.add(new EmoBean("[emo]ue012"));
        emos.add(new EmoBean("[emo]ue013"));
        emos.add(new EmoBean("[emo]ue014"));
        emos.add(new EmoBean("[emo]ue015"));
        emos.add(new EmoBean("[emo]ue016"));
        emos.add(new EmoBean("[emo]ue017"));
        emos.add(new EmoBean("[emo]ue018"));
        emos.add(new EmoBean("[emo]ue019"));
        emos.add(new EmoBean("[emo]ue020"));
        emos.add(new EmoBean("[emo]ue021"));
        emos.add(new EmoBean("[emo]ue022"));
        emos.add(new EmoBean("[emo]ue023"));
        emos.add(new EmoBean("[emo]ue024"));
        emos.add(new EmoBean("[emo]ue025"));
        emos.add(new EmoBean("[emo]ue026"));
        emos.add(new EmoBean("[emo]ue027"));
        emos.add(new EmoBean("[emo]ue028"));
        emos.add(new EmoBean("[emo]ue029"));
        emos.add(new EmoBean("[emo]ue030"));
        emos.add(new EmoBean("[emo]ue031"));
        emos.add(new EmoBean("[emo]ue032"));
        emos.add(new EmoBean("[emo]ue033"));
        emos.add(new EmoBean("[emo]ue034"));
        emos.add(new EmoBean("[emo]ue035"));
        emos.add(new EmoBean("[emo]ue036"));
        emos.add(new EmoBean("[emo]ue037"));
        emos.add(new EmoBean("[emo]ue038"));
        emos.add(new EmoBean("[emo]ue039"));
        emos.add(new EmoBean("[emo]ue040"));
        emos.add(new EmoBean("[emo]ue041"));
        emos.add(new EmoBean("[emo]ue042"));
        emos.add(new EmoBean("[emo]ue043"));
        emos.add(new EmoBean("[emo]ue044"));
        emos.add(new EmoBean("[emo]ue045"));
        emos.add(new EmoBean("[emo]ue046"));
        emos.add(new EmoBean("[emo]ue047"));
        emos.add(new EmoBean("[emo]ue048"));
        emos.add(new EmoBean("[emo]ue049"));
        emos.add(new EmoBean("[emo]ue050"));
        emos.add(new EmoBean("[emo]ue051"));
        emos.add(new EmoBean("[emo]ue052"));
        emos.add(new EmoBean("[emo]ue053"));
        emos.add(new EmoBean("[emo]ue054"));
        emos.add(new EmoBean("[emo]ue055"));
        emos.add(new EmoBean("[emo]ue056"));
        emos.add(new EmoBean("[emo]ue057"));
        emos.add(new EmoBean("[emo]ue058"));
        emos.add(new EmoBean("[emo]ue059"));
        emos.add(new EmoBean("[emo]ue060"));
        emos.add(new EmoBean("[emo]ue061"));
        emos.add(new EmoBean("[emo]ue062"));
        emos.add(new EmoBean("[emo]ue063"));
        emos.add(new EmoBean("[emo]ue064"));
        emos.add(new EmoBean("[emo]ue065"));
        emos.add(new EmoBean("[emo]ue066"));
        emos.add(new EmoBean("[emo]ue067"));
        emos.add(new EmoBean("[emo]ue068"));
        emos.add(new EmoBean("[emo]ue069"));
        emos.add(new EmoBean("[emo]ue070"));
        emos.add(new EmoBean("[emo]ue071"));
        emos.add(new EmoBean("[emo]ue072"));
        emos.add(new EmoBean("[emo]ue073"));
        emos.add(new EmoBean("[emo]ue074"));
        emos.add(new EmoBean("[emo]ue075"));
        emos.add(new EmoBean("[emo]ue076"));
        emos.add(new EmoBean("[emo]ue077"));
        emos.add(new EmoBean("[emo]ue078"));
        emos.add(new EmoBean("[emo]ue079"));
        emos.add(new EmoBean("[emo]ue080"));
        emos.add(new EmoBean("[emo]ue081"));
        emos.add(new EmoBean("[emo]ue082"));
        emos.add(new EmoBean("[emo]ue083"));
        emos.add(new EmoBean("[emo]ue084"));
        emos.add(new EmoBean("[emo]ue085"));
        emos.add(new EmoBean("[emo]ue086"));
        emos.add(new EmoBean("[emo]ue087"));
        emos.add(new EmoBean("[emo]ue088"));
        emos.add(new EmoBean("[emo]ue089"));
        emos.add(new EmoBean("[emo]ue090"));
        emos.add(new EmoBean("[emo]ue091"));
        emos.add(new EmoBean("[emo]ue092"));
        emos.add(new EmoBean("[emo]ue093"));
        emos.add(new EmoBean("[emo]ue094"));
        emos.add(new EmoBean("[emo]ue095"));
        emos.add(new EmoBean("[emo]ue096"));
        emos.add(new EmoBean("[emo]ue097"));
        emos.add(new EmoBean("[emo]ue098"));
        emos.add(new EmoBean("[emo]ue099"));
        emos.add(new EmoBean("[emo]ue100"));
    }

    /**
     * [emo] ue057 ---> 笑脸
     * @param str 普通的字符串 "你好[emo]ue057"
     * @return "你好^_^"
     */
    public static SpannableString getSpannableString(String str) {
        if (! TextUtils.isEmpty(str)) {
            SpannableString spannableString = new SpannableString(str);

            Pattern pattern = Pattern.compile("\\[emo\\]ue[0-9a-z]{3}");
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                String emoId = matcher.group(); // [emo]ue057
                String id = emoId.substring(5); // ue057
                // ue057 --> R.drawable.ue057
                int resId = MyApplication.context.getResources().getIdentifier(
                        id, "drawable", MyApplication.context.getPackageName());
                // R.drawable.ue057 --> bitmap
                Bitmap bitmap = BitmapFactory.decodeResource(
                        MyApplication.context.getResources(), resId);
                // 把bitmap放到一个“imageView”中
                ImageSpan imgSpan = new ImageSpan(MyApplication.context, bitmap);
                // imgSpan放到spannableString中，替换掉spannableString的[emo]ue057
                // SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE 不保留表情含义的字符
                spannableString.setSpan(imgSpan, matcher.start(), matcher.end(),
                        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            return spannableString;
        } else {
            return new SpannableString("");
        }
    }
}
