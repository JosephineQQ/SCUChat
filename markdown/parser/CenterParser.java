
package com.otz.SCUchat.markdown.parser;


import com.otz.SCUchat.markdown.Markdown;
import com.otz.SCUchat.markdown.Markdown.MDWord;

public class CenterParser extends Markdown.MDParser {
    
    @Override
    public MDWord parseLineFmt(String content) {
        return MDWord.NULL;
    }

    @Override
    public MDWord parseWordFmt(String content) {
        if (content.charAt(0)=='{'&&content.charAt(content.length()-1)=='}') {
            int length = content.length();
            return new MDWord(content.substring(1,length-1),length,Markdown.MD_FMT_CENTER);
        }
        return MDWord.NULL;        
    }
}
