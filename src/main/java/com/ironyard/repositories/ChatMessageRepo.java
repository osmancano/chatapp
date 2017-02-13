package com.ironyard.repositories;

import com.ironyard.data.ChatMessage;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by osmanidris on 2/10/17.
 */
public interface ChatMessageRepo extends CrudRepository<ChatMessage,Long>{
}
