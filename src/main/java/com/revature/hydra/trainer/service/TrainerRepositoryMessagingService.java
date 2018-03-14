package com.revature.hydra.trainer.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.beans.SimpleTrainer;
import com.revature.beans.Trainer;

@Service
public class TrainerRepositoryMessagingService {

	@Autowired
	private TrainerRepositoryRequestDispatcher trainerRepositoryRequestDispatcher;
	
	private static final Logger log = Logger.getLogger(TrainerRepositoryRequestDispatcher.class);

	/**
	 * Listener for SimpleTrainerRequests for a single SimpleTrainer
	 *
	 * @param message
	 *
	 * @return SimpleTrainer
	 */
	@RabbitListener(queues = "revature.hydra.repos.trainer")
	public SimpleTrainer receiveSingleSimpleTrainerRequest(String message) {
		log.trace("******************************");
		log.trace("Single Simple Trainer: message was:\n\t" + message);
		log.trace("******************************");
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return trainerRepositoryRequestDispatcher.processSingleSimpleTrainerRequest(request);
	}

	/**
	 * Listener for SimpleTrainerRequests for a List of SimpleTrainer
	 *
	 * @param message
	 *
	 * @return SimpleTrainer
	 */
	@RabbitListener(queues = "revature.hydra.repos.trainer.list")
	public List<SimpleTrainer> receiveListSimpleTrainerRequest(String message) {
		log.trace("******************************");
		log.trace("List Simple Trainer: message was:\n\t" + message);
		log.trace("******************************");
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return trainerRepositoryRequestDispatcher.processListSimpleTrainerRequest(request);
	}
	
	/**
	 * Listener for TrainerRequests for a single Trainer
	 *
	 * @param message
	 *
	 * @return Trainer
	 */
	@RabbitListener(queues = "revature.hydra.service.trainer")
	public Trainer receiveSingleTrainerRequest(String message) {
		log.trace("******************************");
		log.trace("Single Trainer: message was:\n\t" + message);
		log.trace("******************************");
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message);
		JsonObject request = element.getAsJsonObject();

		return trainerRepositoryRequestDispatcher.processSingleTrainerRequest(request);
	}

}