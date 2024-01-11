package kr.co.casa_int.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

/***
 * @author gyutae park
 * @since 2023-08-04 ~ing
 */
public class Article {

    // 전체적으로 not null 추가해야함.

    @Id
    @Column(name = "article_id")
    private int articleId;

    // 추후 jpa 를 이용해서 조인을 할때 필요한 컬럼
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "article_name")
    private String articleName;

    @Column(name = "article_des")
    private String articleDes;

    @Column(name = "article_ctg")
    private String articleCtg;

    @Column(name = "article_size_r")
    private String articleSizeR;

    @Column(name = "article_size_l")
    private String articleSizeL;

    @Column(name = "article_img_url")
    private String articleImgUrl;

    @Column(name = "article_price")
    private String articlePrice;

    @Column(name = "article_inventory")
    private String articleInventory;

    @Column(name = "article_sell_ent")
    private String articleSellCnt;

    @Column(name = "article_always_sell")
    private String articleAlwaysSell;

    @Column(name = "article_hide")
    private String articleHide;

    @Column(name = "article_sale_s")
    private String articleSaleS;

    @Column(name = "article_sale_e")
    private String articleSaleE;

    @Column(name = "article_complete_date")
    private String articleCompleteDate;

    @Column(name = "article_upload")
    private String articleUpload;

    @Column(name = "article_tag")
    private List<String> articleTag;

    @Column(name = "article_sell_Sdate")
    private String articleSellSdate;

    @Column(name = "article_sell_Edate")
    private String articleSellEdate;

    @Column(name = "article_star")
    private String articleStar;

    @Column(name = "article_ban")
    private String articleBan;



}