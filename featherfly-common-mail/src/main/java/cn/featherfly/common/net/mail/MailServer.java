
package cn.featherfly.common.net.mail;

import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * <p>
 * MailServer
 * </p>
 *
 * @author 钟冀
 */
public class MailServer {

    private String host;

    private int port;

    private String protocol;

    /**
     */
    public MailServer(String host, int port, String protocol) {
        AssertIllegalArgument.isNotEmpty(host, "host");
        AssertIllegalArgument.isNotNull(port, "port");
        AssertIllegalArgument.isNotEmpty(protocol, "protocol");
        this.host = host;
        this.port = port;
        this.protocol = protocol;
    }

    /**
     * 返回host
     * 
     * @return host
     */
    public String getHost() {
        return host;
    }

    /**
     * 返回port
     * 
     * @return port
     */
    public int getPort() {
        return port;
    }

    /**
     * 返回protocol
     * 
     * @return protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return protocol + "://" + host + ":" + port;
    }

}
