package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {
    private String campaignID;
    private String campaignStatus;
    private String groupID;
    private String groupStatus;
    private String id;
    private String stateStatus;
    private String label;
    private String typeIdStatus;

}
