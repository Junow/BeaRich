package bearich;

import javax.swing.JTextArea;

public class WalletThread extends Thread{

	private String[] walletinfo;
	
//	ShowFrame sf = new ShowFrame("");
	
	public WalletThread(String id) {
		walletinfo = LoadService.LoadWallet(id);
		
	}
	
	@Override
	public void run(){
		String btc=walletinfo[0];
		String eth=walletinfo[1];
		String dash=walletinfo[2];
		String ltc = walletinfo[3];
		String etc = walletinfo[4];
		String xrp = walletinfo[5];
		String bch = walletinfo[6];
		String xmr = walletinfo[7];
		String zec = walletinfo[8];
		String qtum = walletinfo[9];
		String money = walletinfo[10];
		
		ShowFrame.setBTCPriceArea(btc);
		ShowFrame.setETHPriceArea(eth);
		ShowFrame.setDASHPriceArea(dash);
		ShowFrame.setLTCPriceArea(ltc);
		ShowFrame.setETCPriceArea(etc);
		ShowFrame.setXRPPriceArea(xrp);
		ShowFrame.setBCHPriceArea(bch);
		ShowFrame.setXMRPriceArea(xmr);
		ShowFrame.setZECPriceArea(zec);
		ShowFrame.setQTUMPriceArea(qtum);
		ShowFrame.setkrwArea(money);
		
		
		// asset informaions should be calculated.
	}
}
