package memory;

import MainFrame.Model.BackgroundActor;
import MainFrame.game.MainFrame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Todd on 2/26/2016.
 */
public class CurrentBackground {


    private String name;
    private TextureAtlas backgroundAtlas;
    private TextureAtlas.AtlasRegion region;
    private Sprite sprite;

    static public String background_list;




    private static CurrentBackground instance = null;

    private CurrentBackground(){
        this.backgroundAtlas = new TextureAtlas(Gdx.files.internal("backgrounds/pack.atlas"));
        this.region = backgroundAtlas.findRegion("evansapartment_day_spring");
        this.sprite = new Sprite(this.region);

    }

    public static CurrentBackground getInstance() {
        if (instance == null) {
            instance = new CurrentBackground();
            background_list = "pkeuIfT7 historyclass_day bedroom_day ClCs7pb1 5jIg6p5z ahQIezVK gpvTi8wJ DFWYobkW 1clZzJhv zZAQbpke qO1YSWaM jxdVgOT0 Bq1fDtiq nShStL9F rVChRrNu cjpqTKjY fYl79Jst GHCh9yhe MYk6frkh mBwmilFo 7cIA1P2V u9ktzHJi oPHnl3VV 8GWmOL6A GEzMyK1y 1yx8kUgn XbG79BzU x9xghQsL GfB3BOBu 49Dlw0uN izsxm3iZ gx0hFDeC sHapDrYo i7eMSEl8 xJjBCdZ0 JHhK5fWY iG8t1FoD Ydemw28w 9w1TZ0Be mnHZr7Pm mePKy4DV WfvFLVaa xvI7wU0J L3qQfiRh AFeLkRah t5el3FFw uc2w3sz6 UL8jP4cX iNQfZglZ GXBkn9qY Rp4kYEdL tQujgjEC 0mxnfeea 9pi4eBi9 DtqpIZ1e TlNLOEiU M49myNYT ECweI9A4 fZbIC4Uq MFgrkW1r uGKx4lr6 fCPnhVfR 09IWZbE4 KX8uo95N bDwO7e4P QqkMhOr1 38VFPZ9B ZVfG7HAb hc2CKcWX EMOL688M YWZsvaEY cMNCD6G6 5177Sofc C3VTrjYJ SgEe9W7O 2MZZ2aCF X0VvzFlO cHPBpiCY ahhUn9a6 B1fnAZvK WED1WGEk CdCJbUex z2eP2cKU NnN0xCJ6 G57GRlpu ePO0QgAB gXJz9lfk 83SIlKKj 4tG9zqLc sHCWepp7 0dkguxM2 3blSSBlD h6YzZ4iP G70Si0Mb TbyXZoN0 e01wg9J3 Wyuu4QLm 5KByIp9h DN1TmqNQ Wv6P97Fh odUn81LA lg5PButv RjibET4r IEjRi8Wu 9MvSUhLs 68A8Ygp0 4nza9Xy1 7idMwiic zaBHpzVA 3Ttg1mkk sUqRnE5B QMm6vCDJ aNoLVI3N wfbr5qw3 Gu4AgxzC sVK6dAhj 8ERXiQdl ByZT5bmN l3te5lZY L1MIQe6U OexPv4rs fMXmqsKS HMwARulU dGogMrCi zT5Wf1J0 pxyXExNX TobOrrvt SWkhWk7u ZAAklIB2 Ai89067y 21s5oWo7 0DTPoRIT Evf6h1kW DuyKyvyd slAoeZIl 0uoVf90Z VcVxnQIs VSYlamm1 d4j2gOZl X2y7p6U9 qTPVo0K5 3r5GOnPd SqGclsux l6PtFKfO uCCn6dsT bvkvJT0H IUpwCTke Mf866HkH umVKeFub UHaBUwhh vhqn9onF 8CWbpIa5 xfubte97 LudKquFA hpRng21X CHYnB7eW hI5ugcJi xadfqT6x lBlLBN9U wmMBhxX2 qMBiRe5l DWTppgmd cw99hPQW n60FuMIL 0tw7svtb PbLWrrnC IJjO6qcy mTtS1aIg WA1dRhaC THKB0N52 Xhc80rFC u2C3ZK66 HhGC4pzU lZCXQIem YZjrq9IY aPwITDVS k5emoKqS UUPCnXg8 NINUgcSv 0t949Wn5 Rnv8JBan fJPmquit PJL1vynb kA717BXW C8dmhs8I aJKwIxM3 Pt0tpNP9 f6zLPRxj kd56H0wg dL08t4Ok fxqvh96j bo9UBqp7 GY1a7k4w giaUGIfS PQ1ZE652 DpDN7hBP XbisZDd2 sPoFqZbS wlSqNmQx JOax6qPL FwLnwtyN 899FGi8O M2kpfyhQ oDD790GX rQ5XammN LsqAg4JA SMMLyUk4 EeYnZHm1 txVFA1D9 K6VAKLK1 UbS5FRIO vT9QSIDl hSYhpTe8 x1Hodoll 7TgPPvYQ DvRvHgGh ThRg5zLl pbEHRaa4 PDR2FyzC EQbe0wuS n0oElF63 0OAjPe8u bV0u91to jlt1p7Ry rGNV5Z6g Ksvm7ggo LTWR6f7q PfuCXf44 vLrv2r9H yRpvr9oO 7R1z6OBH NeQyPujz 3uNDVCFH KwHWTqmU YhQ7TUuW z8Oqfb0e 4pl9Q5cJ idvHlwwW ZprB27Lq KzlCCmMm HbOKqlwG sE0K2l9A fdIMUg52 KSinA7QX 4lKgqJQq 7K0DS0WP aqquoXMh bxlBrqke ozTfCBse Hw3eIGEr XXu9jELe ZgMPTICc 9IBnf0pK qMhFXulj LKqaBRRZ BzLGeXM8 9FpdvMbc CoZoGWoC AH9MzGer 7GYonp8G aRsano1J POmpZy4s xX1ELs9f 7Pea7Dc6 7WLwvmvs ymFX8PgN z2EM1U1E trD5HsPa vN620NhI npfyhb7j Eizm38uV SPhFW9Wh 1YyEFgpt 4dIPirKl 3ODhs0ms ry6HgmiY 8GuLGXA2 Zny65ons oy5tATCf QBOLUsTN MRdyARoy 7ehzzBhk SL03SRXm uytKSCrI JJCxyWYV 30zUDwAO D86sxfOW ezHd2fnZ elxI1XuD JmKuGgWk 3BPg6dLO 1uZKZrsq S2Hr1c8H OFtGzi4H 1ZSvg1Jx mDbUzyVl 4LDpRDFH h7Rbx4CM H0AKev64 LZCGnFrS qMW2zFcd xUNkRqwA E0sVpT9e pi0lVifF QXRyp6aJ JHZJoSoh 5cYvr25p i0N9XrC1 GJjqLwwE ccpSfymS Fivd9Eyu 6dU5covg bQVrzR8T paY1sdMB slmheXE6 5GRMU2n5 PNuCj8Cj 7SifIXZJ A6nT1B0t ZN8u1EDB Fx8bdP3U YGwrlsq3 b0ijKxPu KPWPhowc Jss0u9QD f6VtePva 3V0v9pvJ 025pCzil chJdAuFI ZqYwbAlI FRZNIh33 wbIkTX3C y5tABIx5 YJ5wxF9Z 5npHURrg 5ZaQp5Ap 0tL0ry5k IBGAAZCS OPan77Sr 6VDO1jwH rHHT67e9 UaF0rDPZ DaEve0aT 5s4Z0CEx kuK2nnIw KRjnHqPg 1zRTdEeh M6hSEPcO ARSn3C9e bvROoL5x dPldREXx v5aIcigb lKLF2ag3 1eO0DqDr 2GBMpI6k 35jmTdk8 KGfx1QVg qTdJHUIP JuzDxvZU Z2DMKXvb nPexPAN8 tOJM3Z8T NLYw0YcI asIkkYAx TpcJUao6 7iqBUkfB BMQWCdXo fjkDXQ5n wFBt97gs yCIYlbTg HGdSQslN nvFDHbjQ pKL9Errj sMhA3Wyt TymPpoGr M4Hpqocw Yh7HU21i hpbP3uIZ NL1U3WWx tSgCLqdC iVPh2oeC b8gtYlWO AicALY3s Gh35bidQ 5xALmokT liYciI7j AKMKaLk4 vn4zgYVW TOmTnD1B 0535f4QH rWBT0aUX CAmARnAi Xxga1r4x GLwqMS80 r5IJChRi Jo01BRLb qHwjU2lO EFdHklFl 2gCinSCE i5evWKN6 QRpsG0Pt 0AMr7sEJ NptNZneo 5KepUBvG HS0T5VcB yTuUcO4N rQfXGRYK WH7S2NeN yl0B8ekK CEIn7kUE p8vPM379 NYyNZJDz UN8avB2D L5T0Oygy GDAfSiOz yc7B15ln uJY88tzF ARj4TSTy tTAcAgG6 rSiOmTYX PIyZxwtC 5neQZXmT VFcCE0kx UrUkTazC VDXbkPFv IKFhfQdY rPXGcxWe D2F3PeXx djdtmA5Q dms1yUpi iGGwG1vA mP1iLjls 6rMOxydd kOvpmA9e Tg8Zvf1R 4iy6BvOm 7GDRpivG gpzr3vJX nHOH9BBD 4L2qv48q i3iq3OLd FbP4jAfz 7sP0YyiE fCyH5uNQ DiNEXXQy zAmMPqeE fXmXqo0h sVvp4pOm xdh2BuU4 W8UNgxaC VLwwAJ75 hcNA5OHo UxKjK6DP gh2iffBE 3uKPPbII DDG5t8Cf AnC6xPJ7 Lpoi5cLE KcIb6UwI OA4Rrb0P hd4Gy6tw q9ObfxKT 0KvGierI yMAYBu3l u4ipMzW4 o9wmEzgt XUn7ZsGp qFGwVWAd 93Etps5a XYUgu2Gf o5lcrcCq 9RTrYDdP dZn4eNv8 2LwVCBhO CRxbSLMb mttpa820 gzY160Hp 7ARBfROe dQgE09Ym Clcgbvjw 60BmPfVK ngZZ70XA yH2VDVJJ Dmverueg NGk30tco I2b156SQ PMylzs7F O2Ar3F1t GbPVgs1e u39k0Q9T 7JGxIPnG pDi7WsJm J2fySbsE JHthO9nR fnpkvYQA AiPtSPYM uRBgsVUl Gqhyl5TG BuSWw93O xRzI0YeU iArqSbKy 76N3xTMs iHadLT7p 18HVE28b kUoC8xS6 vepTOx3q O2wptWJq qIqHDWBI fp4Gjf0y ZggiZHDb zX1T82gG Lb9SJv0s fQnksQ3d Dtu8oYsL uL1bZbSm ZKdKJzDO 4uAxULrP joXEMXYE RRMyEn5O 1Ilm9jq0 wULCYoX5 wPUTtmSL vdMhcXJY nbRyfHr4 d40Gcno4 XnsbKYW2 3t4LQr3C UdCACQDk WKz2AkVG GLXmpmQK FFyoemeq 4xOQkMcu 7qVfa4tp XLO4qW1u N2mpCeOF 5zl0Zr0f w5UtkUAA ZKeWZxXi 9egA5apR sz59iUUj loOMpHea H4tVNaI0 K2KiMdqf yFJq4KSi YFZVa6aS qUkgVpqx WMRxukFt dBFcNZEk 56zqdLzS htadwgN3 cw58BcxK LrbwEF4D xAlbTiLF 1a4whDEo voKplUQN tw5kUcaH u40ckBur kUfsjINO B6QIem3W wqERr824 W68Xx1TQ ZDO6fsAK b4RhHPxj wXwvVUfA EfyZfFpI OYRJZcWq rhzGgMZZ YYtp4AvL Mk5Ufe1q 72it72mK m9s2VGFL opxTVemE Gdx7sUhC qVTS1SIr twN6QTHV 1s1LaqLJ pXuVmgm3 HSW8litT ouvFN9tT 5fmj0NCZ Zqalj2a2 Ccebfced qYxOqDOv RS2mzxUK WDrb9gnm ZQYxyoIP CrajU8fM 313VS8om CeZMH8e3 lGrcsAH3 K16aEOte 3jJswEIR lC1FiM7m tj6vsHAq H48miw5y TBsvLXZR CzGigOQ0 eijB20rJ IjiIoWIs Nx9qFbNc lgMTb9rX Xa2sf6gh bP4GjnWD vAbUXBdf Dw24G8JM 7pjCnZZs t5L5lFHC oQWQl0tA 8U2LLKY4 tkVWB6AY HZBpk3wf xBo7VHS4 ZdoxGBot jJNqG0Ug T9O0jAPu YIYlEtJc piefwdSS RlPDup8X UIqdXnHh Ozt94Rzc KIdJTGoa c8ZvMFk2 WGAy5ZuE YwwRJLBn msS2ELFx gSrSvPNa F2MfjBTf 0IAfYz5P ZyMp3fdk PkfMGB1O bFwkiFcL XMX1BJrR F8zP0nCj U8umJrcQ NA6S0A5W 3vCrHnXV QXSMXwDk qoPSKnbr O1jMlgnx 56lKLwQP UhT6pC3S pitX5F5L 3G8lQeUE RsbGoEPt 1Oayn3gp dqXST8l1 cTtoQYJy bqw9T6Gh YnD0Joia 5mAp3uDk MA6FwHCt P4IEvCYA Fwaew88U nxNZFL5Y svU0iGqM FCCWpQAE yDdWdxir RF6zDAE4 oLHXCiYM b0ZlcBNf a3lRNRTT Gzpx38kT KYMufndn BnbqsAFZ zAiiNaf5 0Kz5O3P0 vMa0MHGC v0HGgA8R ZCUQxKFb MeH6GX7f vmLrVOum Y0zcs2kY xOqgNfKn NQTjcrfR dMCQClg1 oB9yyAuU QrKdAd0W OnRHVKeL DSbpcBuN BAjosXb6 TVAgyZ1d dRdxXfQt PKOPVj8f bgIodjbP QYFhGIsH y0aDhHDw 48H4rAWK h1Gwm5ov DXajsui0 fqgiVP9y ssLUbtbk U3QvzqU9 tS3e57FL eC1Jf8Kb YAFV0Mwj 1za8Et2t yeqUpAkT tb4g6JgB xk77i0zc 43tdlLGd UfMYCes8 5kbZ6AwN 9gZep9Fs 9c9E0eNs rTejVBmu B9nYLuGq WjhhUY3x yM6ityzf lGllAtX4 pPsS7tBG ksmez5AE lQcpJ5Lf JuENxIiJ 5csciawx Mm38UzQS yK1yLCJF mblAWZSV VtaMhjpu CjEJ54tb 2LWm7OV1 8r0EpAIG WTZl7Kbq qZpk0T9w V1c20URt 7po9ObAK 3N5igzWX 7RZ2XbCs c0eOBorf 2gkQz89e EFSaz5mX qjTn0UK9 uQCV6DiW IbkE6uli pt6zziKH iqgeQB25 uRmi1W5a ngzATkPB wcRx4TwX 64hEHUmN 8Q5KwtGM 0Rbo49Tt MbR3pnSY 7mM7VpyZ ygQtas9m RAwhGKR8 aCp3aA8K 2OxriWJ5 pxHWvbuY Tj0vqdVu IbkCXRcL 2E7Znbue 6H60P18t yjH0JSsO gXfgeFL9 Omb2JfuZ hAsb26Gn Up0tHK2S 0TypqIb4 6KHK0pw1 l2X9kqrq xiUOGQKr GMRnejWz 8clvtzYw 0vBsDyHp CR1wbGdn ZJoF6sRV IrZF79LN DjGtU7FM qeQqhqy9 fDJJxSSK B2wNrvbM MSb81ci2 3Bmi1Ltu T6Q0L7f7 kUr6KWPz zsem5KJS VmDziRjy UPUY4rkf KiHJlXMv xotMxXIT 7tukmmyp Zswq3TIz PBlwo5k3 tHfecwMs x8DSZE5u pdK1uRsW Yi9u5eaG A4r96qdY DmzncTOx BTsz5emv jplN6ALu yAdNtdpG IPxWLfpR nbYYstZQ bqIsysVo Le8xJUJs cDEZpkhN lvGqznW3 7P5Adna4 Shj2LbW3 7yUvHulB wxRK0XVh N17JzKYC qO4lkvV2 NLo39WMl ehGvuhhX G5GTCIkB Py9VXVWT 3hJVxG7I IrZPd8OE OTYeutjH Wn4Bwykl sDLpjFeo zNLfzlRm BoMLxcpj INAD5rir 9GcN2Ks9 OQP4VJpj Qv1UB6vh Zfj1Ev7w MBZ7hqze RqShIyP1 Ywhboj6Q p4jOMvrY QHyC0f2i saQBB6kz pnRNnsGZ bwqHifzH GThN8eNG 6F0d3mNl c10CEUPa 2BRopys3 lPRVyczM yNilnj0l lk5MDG04 5eh8mdl1 ZPgh16tI 0wRyQekt l9anu8mZ A4hUlkYQ Bf2DCfpW kkgjxLOK HhfAf2F0 q2FSEDoA bxFBAWWs 1fcmfLmO hsO47Fye BCwhzZd8 yVnRhBEx abjjY3Ni 8hatxnFh LjPRNIcz hIZ3LBap LpxK0XWM Wys9R3st qfWMJno9 pSZJKMeq aWfcj3G4 zun9VO6S IFBHnAmt Y3PE6Tcr Uv2nYcZV UCx7XWGe q9ANktv5 T58fWZJ0 jDpp3jIw GZGjnamM Ow0UL5fq DsXpOlkj eD4CKCHV Znt99oS9 kBgpUJVK rYL3RPL0 qlp5mNAq Z1QjOMkJ NIleKMKv rSWIxAdS q7ZptJwS 4mVWccfb hB83nxQx u6iLOPkE 20C67tBB Y57c47TH 9KiwV4xr cNZlDbuE rYfb6ybv sI7fG58e 94wS4LNm g2DTFK80 xbju6Rwr zNEzvSEM vsAqhCIg Kpa1YvEZ H9L2TRbN ZNDwdWDY Da8blXpi M8xlk9kW 8U4IWUfA xgWCbuk0 JeH7Ac0a sAtDxH5F oV6obxh6 ";

        }
        return instance;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (name == null){
            Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.BLACK);
            pixmap.fill();

            region.setTexture(new Texture(pixmap));
            pixmap.dispose();

        }
        else {
            this.name = name;
            region = backgroundAtlas.findRegion(name);
        }
        //this.sprite = new Sprite(this.region);
        BackgroundActor current = new BackgroundActor(region);
        MainFrame.addActor(current, true);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
