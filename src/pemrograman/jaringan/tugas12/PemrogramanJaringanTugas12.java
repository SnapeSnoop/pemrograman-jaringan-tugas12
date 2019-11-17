/*
 * pemrograman-jaringan-tugas12

 * Copyright (c) 2019
 * All rights reserved.
 * Written by od3ng created on Nov 13, 2019 9:04:40 AM
 * Blog    : sinaungoding.com
 * Email   : lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */
package pemrograman.jaringan.tugas12;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author od3ng
 */
public class PemrogramanJaringanTugas12 implements Runnable {

    private GreetClient client;
    private Port portView;
    private ChatGui view;
    private String mess = "";

    public PemrogramanJaringanTugas12() {
        this.client = new GreetClient();
        this.portView = new Port();
        this.view = new ChatGui();
        this.portView.setTitle("Port Input");
        this.portView.setVisible(true);

        this.portView.getBtnOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(portView.getPortText().getText()).equals("6666")) {
                    client.startConnection("127.0.0.1",
                            Integer.valueOf(portView.getPortText().getText()));
                    portView.setVisible(false);
                    view.setTitle("Client Chat");
                    view.setVisible(true);
                } else {
                    portView.getPortText().setText("");
                }
            }
        });
        this.view.getBtnKirim().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mess += time() + "-> " + String.valueOf(view.getTextField().getText() + "\n");
                String response = client.sendMessage(time() + "<-" + String.valueOf(view.getTextField().getText()));
                mess += response + "\n";
            }
        });
    }

    public String time() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        String time = simpleDateFormat.format(date);
        return time;
    }

    @Override
    public void run() {
        do {
            if (this.view.getTxtArea().getText().equals(mess) == false) {
                this.view.getTxtArea().setText(mess);
            }
        } while (true);
    }

    public static void main(String[] args) {
        new Thread(new PemrogramanJaringanTugas12()).start();
    }
}
