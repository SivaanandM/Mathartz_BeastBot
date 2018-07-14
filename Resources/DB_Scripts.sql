'Contract Bank here we are saving all head and player deals with'

Create table TBL_Master_Contracts  (id bigint auto_increment, SECID varchar(50) not null, SYMBOL varchar(50) not null, 
EXCHANGE varchar(50) not null, INSTRUMENT varchar(50) not null, LOTSIZE varchar(50) not null, TICKSIZE varchar(50) not null, 
EXPDD varchar(50) not null, EXPMMMYY varchar(50) not null, OPTTYPE varchar(50) not null, STRIKE varchar(50) not null);

'CENTRAL DATE TABLE'

CREATE TABLE TBL_CENTRAL_DATE (id bigint auto_increment, DATE varchar(50) not null);

'Trade Line Table'

Create table TBL_Trade_Line  (id bigint auto_increment, HEADID varchar(50) not null, HEADDISPLAY varchar(50) not null, HEADSYMBOL varchar(50) not null,
PLAYERDISPLAY varchar(50) , PLAYERID varchar(50), SYMBOL varchar(50) ,
EXCHANGE varchar(50), INSTRUMENT varchar(50) , LOTSIZE varchar(50), TICKSIZE varchar(50) , 
EXPDD varchar(50) , EXPMMMYY varchar(50) , OPTTYPE varchar(50) , STRIKE varchar(50) );

'BEAST VIEW'
Create table TBL_BEAST_VIEW  (id bigint,  HEADDISPLAY varchar(50) not null, 
PLAYERDISPLAY varchar(50) not null, F1POINT varchar(50) not null,  F2POINT varchar(50) not null,  F3POINT varchar(50) not null,  
F4POINT varchar(50) not null,  F5POINT varchar(50) not null,);

'FORMULA DATA'

CREATE TABLE TBL_FORMULA_DATA (ID bigint, X double, Y double, ST varchar(25), MT varchar(25),
ET varchar(25), LCOUNT int, ROUND int, QTY int, TRADESWITCH Bool )