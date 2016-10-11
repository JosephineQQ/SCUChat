
package com.otz.SCUchat.markdown.parser;


import com.otz.SCUchat.markdown.Markdown;
import com.otz.SCUchat.markdown.Markdown.MDWord;

public class UnOrderListParser extends Markdown.MDParser {
    
    private static final char KEY = '-';

    @Override
    public Markdown.MDWord parseLineFmt(String content) {
        if (content.charAt(0)!=KEY) {
            return MDWord.NULL;
        }
        return new MDWord("",1,Markdown.MD_FMT_UNORDER_LIST);
    }

    @Override
    public MDWord parseWordFmt(String content) {
        return MDWord.NULL;
    }

}
