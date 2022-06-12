package tech.GlavTech.SD2022.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer trackingCode;

    //@ManyToOne specifies that AdmiredUser is an embedded class to User, at least I believe.
    //      It's description was a little confusing.
    //FetchType.Lazy allows us to not initialize parts of the object for as long as possible.
    //Admired User = someone the current user is following
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User admired;
    //ID allows for "safer" lookup
    private Integer admiredID;

    //Admiring User = someone that is following current user
    @ManyToOne(targetEntity = User.class, fetch= FetchType.LAZY)
    private User worshiper;
    private Integer worshiperID;

    public Follower(User worshiper, User admired) {
        this.admired = admired;
        this.worshiper = worshiper;
    }

    public Follower(Integer admiredID, Integer worshiperId) {
        this.admiredID = admiredID;
        this.worshiperID = worshiperId;
    }



}
