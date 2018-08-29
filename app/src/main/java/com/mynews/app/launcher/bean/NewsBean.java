package com.mynews.app.launcher.bean;

import com.common.http.bean.BaseBean;

import java.util.List;

/**
 * @author jzy
 * created at 2018/5/24
 */
public class NewsBean extends BaseBean{

    /**
     * id : 16595975225250496
     * fullText : https://cpu.baidu.com/api/1022/c8f42477/detail/16595975225250496/news?im=qXPjFXbHesEeZ27hjr5PNgpQkIUlgHC4xMmq-204A23lpHw1Qd-frMk13rXugcSiVGg5sZDlHWd1HE9X7FYm9BEz8bNfEnXFRNz0S4bjKdX3z1JV3XEMBOGO-aB5_N4SV-CwQ6788Ia3m4u4PjJSPBJGPPBnIFUS9kVbZ-NNRfiygFqbfRqbig3q_ie75AZbcY2ja0C8CDzITN2wy43VhJIwAUQJ9tRCtVmHmGKuxn4NXeeFQslFoQ4NgxKh3D8QQ7jmBoBS1r7ckPZKZANdk9ZXOzJoO8SAAGX3FqNzGFRctsfoCkc5IFJSh91R2RoyGCuvnCqAzFP__mM9tiF8kA&aid=SkeKObG_RfWh07ZwLnentCT23gBXVoDMWtmOX4I8Z0mg5b5fqBqQz6tE6mnktMCyfql7HWiy3zdvaNjP7QTnq-sEpdy4loAFD_POafTVwZGOe3FRcjYr1ycJoW-qy4epIpeKFuWt0kBUZ0OQIhwV5YILky9QRt00MQjlL83eM1f2jiTcyd6Kmzn1jvgOAPgLyiAKA7zt39TquStQM0zKFERw-5TPCrXVskK-p1SlYNWB7n9Gcb_p4AIskXzRJZfvPLL1VX6zb_2GvZfXX_wPgLO3l1TTJwAi58u9HqVktWy4l2Zj5LJ64_zADsnZA73aQDHSPnUSs3PnzOIzIXAtZg&imMd5=iVzMEZwmS2UZ4LWWP5vIfOdtaisgmdG-8M9IJqNqrfKOlmMztByuq12pQ-2YNkXM3EUVT-9f2TwZW-naFscp-QPYGd-c7vbEH4pOikJDzEWBhDv7IlIV0wb72jcP9ULMhtjKH-3EG3SUZOgwLQjJj9hSdkYs_I1t50fWfJvEEHfMP-MaJV-85uT6h9ameVdaDJVi1OZtfrJAazSvMGzKcqLVqnvhCAX6l5QwP6GWvGfyZgemFeRNwXSlR0QoCZNsacoCvO6wu33v1o7rHxQnD2bhtlKoIxquUXAZEZ92FYL0s3HgsbeY_aoqgscrGKsh5Te-rNDErOZ63UVHpdULuQ&cpid=eNF0jm98Rde_HFo961FGryAMIHSU8UnK4dABpBQFuk-Yzs1pxJd7pOwyex6Gqsp-O8jOi4rl4MzhG18VzZ-TxyEYKNguHTSP1CmvthoiN11Ka_82qOEMunBqwmabNJSw54O4sLbhE50Tx2JQAjwaBYdZtW0W9t_KD4p9u1BMhbF0kpGdqu6yWki__kyGOpUDlNcw4Lfx_iNE4mvj3tycvh0RVxCAjKjnmbcyXjRlNzvc8xROoyqtnQ31NIpn7oD9Pe49JDZ98xtBcLqxHf8KAFfeBs2-34JRQwvnoqBuMqVeoi760MtZNow6Li9TienYu3WRUR2WOQ8Rl5XFSLb7gw&scene=0&log_id=15271319589199ca8859ca632bf81324&exp_infos=9241_21500_21501_21901_21953_21961_22641_23021_23632_24324_25213_25311_25411_26002_20011_8000200&no_list=1&forward=api&api_version=2&rt=20&rts=1048576
     * images : ["https://publish-pic-cpu.baidu.com/27157c32-7a38-4569-85a5-9ccd83dee79c.jpeg@w_228,h_152","https://publish-pic-cpu.baidu.com/638dc9ad-ef19-4dab-a82e-93a04dd86564.jpeg@w_228,h_152","https://publish-pic-cpu.baidu.com/e484e0a8-749c-4ee5-af6f-bffc6ad2ce67.jpeg@w_228,h_152"]
     * articleImages : []
     * shareUrl : http://share.dev.yousupeisong.cn/shareLink.html?source=aHR0cHM6Ly9jcHUuYmFpZHUuY29tL2FwaS8xMDIyL2M4ZjQyNDc3L2RldGFpbC8xNjU5NTk3NTIyNTI1MDQ5Ni9uZXdzP2ltPXFYUGpGWGJIZXNFZVoyN2hqcjVQTmdwUWtJVWxnSEM0eE1tcS0yMDRBMjNscEh3MVFkLWZyTWsxM3JYdWdjU2lWR2c1c1pEbEhXZDFIRTlYN0ZZbTlCRXo4Yk5mRW5YRlJOejBTNGJqS2RYM3oxSlYzWEVNQk9HTy1hQjVfTjRTVi1Dd1E2Nzg4SWEzbTR1NFBqSlNQQkpHUFBCbklGVVM5a1ZiWi1OTlJmaXlnRnFiZlJxYmlnM3FfaWU3NUFaYmNZMmphMEM4Q0R6SVROMnd5NDNWaEpJd0FVUUo5dFJDdFZtSG1HS3V4bjROWGVlRlFzbEZvUTROZ3hLaDNEOFFRN2ptQm9CUzFyN2NrUFpLWkFOZGs5WlhPekpvTzhTQUFHWDNGcU56R0ZSY3RzZm9Da2M1SUZKU2g5MVIyUm95R0N1dm5DcUF6RlBfX21NOXRpRjhrQSZhaWQ9U2tlS09iR19SZldoMDdad0xuZW50Q1QyM2dCWFZvRE1XdG1PWDRJOFowbWc1YjVmcUJxUXo2dEU2bW5rdE1DeWZxbDdIV2l5M3pkdmFOalA3UVRucS1zRXBkeTRsb0FGRF9QT2FmVFZ3WkdPZTNGUmNqWXIxeWNKb1ctcXk0ZXBJcGVLRnVXdDBrQlVaME9RSWh3VjVZSUxreTlRUnQwME1RamxMODNlTTFmMmppVGN5ZDZLbXpuMWp2Z09BUGdMeWlBS0E3enQzOVRxdVN0UU0wektGRVJ3LTVUUENyWFZza0stcDFTbFlOV0I3bjlHY2JfcDRBSXNrWHpSSlpmdlBMTDFWWDZ6Yl8yR3ZaZlhYX3dQZ0xPM2wxVFRKd0FpNTh1OUhxVmt0V3k0bDJaajVMSjY0X3pBRHNuWkE3M2FRREhTUG5VU3MzUG56T0l6SVhBdFpnJmltTWQ1PWlWek1FWndtUzJVWjRMV1dQNXZJZk9kdGFpc2dtZEctOE05SUpxTnFyZktPbG1NenRCeXVxMTJwUS0yWU5rWE0zRVVWVC05ZjJUd1pXLW5hRnNjcC1RUFlHZC1jN3ZiRUg0cE9pa0pEekVXQmhEdjdJbElWMHdiNzJqY1A5VUxNaHRqS0gtM0VHM1NVWk9nd0xRakpqOWhTZGtZc19JMXQ1MGZXZkp2RUVIZk1QLU1hSlYtODV1VDZoOWFtZVZkYURKVmkxT1p0ZnJKQWF6U3ZNR3pLY3FMVnFudmhDQVg2bDVRd1A2R1d2R2Z5WmdlbUZlUk53WFNsUjBRb0NaTnNhY29Ddk82d3UzM3YxbzdySHhRbkQyYmh0bEtvSXhxdVVYQVpFWjkyRllMMHMzSGdzYmVZX2FvcWdzY3JHS3NoNVRlLXJOREVyT1o2M1VWSHBkVUx1USZjcGlkPWVORjBqbTk4UmRlX0hGbzk2MUZHcnlBTUlIU1U4VW5LNGRBQnBCUUZ1ay1ZenMxcHhKZDdwT3d5ZXg2R3FzcC1POGpPaTRybDRNemhHMThWelotVHh5RVlLTmd1SFRTUDFDbXZ0aG9pTjExS2FfODJxT0VNdW5CcXdtYWJOSlN3NTRPNHNMYmhFNTBUeDJKUUFqd2FCWWRadFcwVzl0X0tENHA5dTFCTWhiRjBrcEdkcXU2eVdraV9fa3lHT3BVRGxOY3c0TGZ4X2lORTRtdmozdHljdmgwUlZ4Q0FqS2pubWJjeVhqUmxOenZjOHhST295cXRuUTMxTklwbjdvRDlQZTQ5SkRaOTh4dEJjTHF4SGY4S0FGZmVCczItMzRKUlF3dm5vcUJ1TXFWZW9pNzYwTXRaTm93NkxpOVRpZW5ZdTNXUlVSMldPUThSbDVYRlNMYjdndyZzY2VuZT0wJmxvZ19pZD0xNTI3MTMxOTU4OTE5OWNhODg1OWNhNjMyYmY4MTMyNCZleHBfaW5mb3M9OTI0MV8yMTUwMF8yMTUwMV8yMTkwMV8yMTk1M18yMTk2MV8yMjY0MV8yMzAyMV8yMzYzMl8yNDMyNF8yNTIxM18yNTMxMV8yNTQxMV8yNjAwMl8yMDAxMV84MDAwMjAwJm5vX2xpc3Q9MSZmb3J3YXJkPWFwaSZhcGlfdmVyc2lvbj0yJnJ0PTIwJnJ0cz0xMDQ4NTc2&invite_code=0
     * title : 他长相丑陋饰演坏蛋，乘出租车被拒，女儿却美若天仙
     * duration : 0
     * playCounts : 0
     * updateTime : 1526985976
     * source : 织女的小天地
     * replyNum : 0
     * isApi : 1
     */

    private String id;
    private String fullText;
    private String shareUrl;
    private String title;
    private int duration;
    private int playCounts;
    private int updateTime;
    private String source;
    private String replyNum;
    private int isApi;
    private List<String> images;
    private List<?> articleImages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlayCounts() {
        return playCounts;
    }

    public void setPlayCounts(int playCounts) {
        this.playCounts = playCounts;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(String replyNum) {
        this.replyNum = replyNum;
    }

    public int getIsApi() {
        return isApi;
    }

    public void setIsApi(int isApi) {
        this.isApi = isApi;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<?> getArticleImages() {
        return articleImages;
    }

    public void setArticleImages(List<?> articleImages) {
        this.articleImages = articleImages;
    }
}
