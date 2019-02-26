package com.stuart.stuartapp.demo.btc;

public class CoinInfo {
  /**
   * {
   "ticker" : "HUOBIPRO:BTCUSDT",  // Ticker名称
   "exchangeName" : "HuobiPro",   // 交易所名称
   "base" : "BTC",   // 交易币种
   "currency" : "USDT",  // 兑换币种
   "symbol" : "BTCUSDT",   // 交易对儿标识
   "high" : 61512.12713304667350,    // 最高价
   "open" : 58970.63530801362825,   // 开盘价
   "close" : 59065.33243911680550,   // 收盘价（最新价）
   "low" : 57920.51289144851775,   // 最低价
   "vol" : 22373.79282497,   // 成交量
   "degree" : 0.16058400,   // 涨跌幅
   "dateTime" : 1524630942000   // 更新时间戳（微妙）
   }
   */

  public String ticker;
  public String exchangeName;
  public String base;
  public String currency;
  public String symbol;
  public String high;
  public String open;
  public String close;
  public String low;
  public String vol;
  public String degree;
  public String dateTime;

}
