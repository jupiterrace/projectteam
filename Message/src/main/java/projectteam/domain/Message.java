package projectteam.domain;

import projectteam.MessageApplication;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;


@Entity
@Table(name="Message_table")
public class Message  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    private Long msgId;
    private Long gameId;
    private Long customerId;
    private String content;


    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public static MessageRepository repository(){
        MessageRepository messageRepository = MessageApplication.applicationContext.getBean(MessageRepository.class);
        return messageRepository;
    }


    public static void sendReleaseDate(RelesedateComed relesedateComed){

        Message message = new Message();
        /*
        LOGIC GOES HERE
        */
        repository().save(message);


    }

    public static void sendDownloadComplete(DownloadCompleted downloadCompleted){

        Message message = new Message();
        /*
        LOGIC GOES HERE
        */
        repository().save(message);


    }

    public static void sendDownloadCancel(DownloadCancelled downloadCancelled){

        Message message = new Message();
        /*
        LOGIC GOES HERE
        */
        repository().save(message);


    }

    public static void sendDownloadStart(DownloadStarted downloadStarted){

        Message message = new Message();
        /*
        LOGIC GOES HERE
        */
        repository().save(message);


    }

    public static void sendPurchaseCancelMsg(PurchaseCancelCompleted purchaseCancelCompleted){

        Message message = new Message();
        /*
        LOGIC GOES HERE
        */
        repository().save(message);


    }

    public static void sendPurchaseConfirmMsg(PurchaseCompleted purchaseCompleted){

        Message message = new Message();
        /*
        LOGIC GOES HERE
        */
        repository().save(message);


    }

}
