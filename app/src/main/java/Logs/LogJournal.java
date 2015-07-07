package Logs;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Log journal class, it stores logger messages
 *
 * @author Anton Korenev
 * @version 1.0
 */
public class LogJournal implements Serializable{
    /** Tag for logcat */
    private static final String sTag = LogJournal.class.getSimpleName();

    /** List of lists with tag and it's messages */
    List<LinkedList<LogMessage>> mTagLists;

    /**
     * Default constructor
     */
    public LogJournal(){
        mTagLists = Collections.synchronizedList(new LinkedList<LinkedList<LogMessage>>());
        for(int i=0; i< LogTag.values().length; i++){
            mTagLists.add(new LinkedList<LogMessage>());
            //TODO:создается список для тэга ALL, но никогда не используется, убрать или применить
        }
    }

    /**
     * The method for adding message to journal
     * @param message added message
     */
    public void addMessage(LogMessage message){
        mTagLists.get(message.mLogTag.mValue).add(message);
    }

    /**
     * The method for getting all messages of certain tag(category)
     * @param logTag search tag
     * @return list of tagged messages
     */
    public List<LogMessage> getMessagesByTag(LogTag logTag){
        List<LogMessage> messages = new LinkedList<>();

         // if tag is ALL then gathering all tag arrays into the one
        if(logTag.equals(LogTag.ALL)) {
            //все в одну кучу, чтобы лишний раз не хранить
            for (List<LogMessage> tl2 : mTagLists) {
                messages.addAll(tl2);
            }
            //сортируем по дате от большей
            Collections.sort(messages, Collections.reverseOrder());
        }
        else {
            //берем массив по тегу
            messages.addAll(mTagLists.get(logTag.mValue));
        }
        return messages;
    }

    public void clear(){
        for(LinkedList<LogMessage> taglist: mTagLists){
            taglist.clear();
        }
        mTagLists.clear();
    }
}

