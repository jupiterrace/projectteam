package projectteam.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="View_table")
public class View {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long viewId;
        private Long gameId;
        private Long reviewCount;
        private String purchaseStatus;
        private String downloadStatus;
        private Long customerId;
        private String version;


        public Long getViewId() {
            return viewId;
        }

        public void setViewId(Long viewId) {
            this.viewId = viewId;
        }
        public Long getGameId() {
            return gameId;
        }

        public void setGameId(Long gameId) {
            this.gameId = gameId;
        }
        public Long getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(Long reviewCount) {
            this.reviewCount = reviewCount;
        }
        public String getPurchaseStatus() {
            return purchaseStatus;
        }

        public void setPurchaseStatus(String purchaseStatus) {
            this.purchaseStatus = purchaseStatus;
        }
        public String getDownloadStatus() {
            return downloadStatus;
        }

        public void setDownloadStatus(String downloadStatus) {
            this.downloadStatus = downloadStatus;
        }
        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }
        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

}