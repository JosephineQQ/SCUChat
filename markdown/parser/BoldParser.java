
package com.otz.SCUchat.markdown.parser;

import com.otz.SCUchat.markdown.Markdown;
import com.otz.SCUchat.markdown.Markdown.MDWord;


public class BoldParser extends Markdown.MDParser {
    
    private static final String KEY = "**";

    @Override
    public Markdown.MDWord parseLineFmt(String content) {
        return MDWord.NULL;
    }

    @Override
    public MDWord parseWordFmt(String content) {
        if (!content.startsWith(KEY)) {
            return MDWord.NULL; 
        }
        int position = content.indexOf(KEY,2);
        if (position==-1) {
            return MDWord.NULL;
        }
        return new MDWord(content.substring(2,position),position+2,Markdown.MD_FMT_BOLD);
    }

}
