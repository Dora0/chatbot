package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class Callback {

	@Autowired
	private LineMessagingClient lineMessagingClient;

	@EventMapping
	public void handleText(MessageEvent<TextMessageContent> event) {
		System.out.println("event: " + event);

		/* LINEのユーザIDを取得 */
		String userid = event.getSource().getUserId();

		/* リクエストの文言を取得 */
		TextMessageContent tmc = event.getMessage();
		String text = tmc.getText();

		// Reply用Listの用意
		List<Message> replylist = new ArrayList<>();

		replylist.add(new TextMessage("おうむ返しします。"));
		replylist.add(new TextMessage(text));

		/* Line reply */
		lineMessagingClient
		.replyMessage(new ReplyMessage(event.getReplyToken(),
				replylist));

	}

	@EventMapping
	public void handleEvent(Event event) {
		System.out.println("event: " + event);
	}

}
