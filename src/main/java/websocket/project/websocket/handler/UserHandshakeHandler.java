package websocket.project.websocket.handler;

import com.sun.security.auth.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import websocket.project.websocket.model.User;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Component
public class UserHandshakeHandler extends DefaultHandshakeHandler {
    private static final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);
    public static User user;

    /**
     * Cria o Handshake com o usuario recebendo uma request o websockethandler enviado pelo Stomp no javascript
     * o usuario tem apenas um uuid random para indetificar
     * TODO -> cirar um model para usuario inves de usar o do java security
     * */
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        user = new User();
        LOG.info("Usuario " + user.getName() + " fez o handshake!");
        return user;
    }
}
