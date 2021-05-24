package com.gavin.foo.crud.log.util;

import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Enumeration;

/**
 * 网络通讯工具
 */
public class InetUtils {

    private final Logger log = LoggerFactory.getLogger(InetUtils.class);

    public HostInfo findFirstNonLoopbackHostInfo() {
        InetAddress address = findFirstNonLoopbackAddress();
        if (address != null) {
            return convertAddress(address);
        }
        return null;
    }

    public InetAddress findFirstNonLoopbackAddress() {
        InetAddress result = null;
        try {
            int lowest = Integer.MAX_VALUE;
            for (Enumeration<NetworkInterface> nics = NetworkInterface
                    .getNetworkInterfaces(); nics.hasMoreElements(); ) {
                NetworkInterface ifc = nics.nextElement();
                if (ifc.isUp()) {
                    log.trace("Testing interface: " + ifc.getDisplayName());
                    if (ifc.getIndex() < lowest || result == null) {
                        lowest = ifc.getIndex();
                    } else if (result != null) {
                        continue;
                    }
                }
            }
        } catch (IOException ex) {
            log.error("Cannot get first non-loopback address", ex);
        }

        if (result != null) {
            return result;
        }
        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            log.warn("Unable to retrieve localhost");
        }
        return null;
    }


    public HostInfo convertAddress(final InetAddress address) {
        HostInfo hostInfo = new HostInfo();

        String hostname;
        try {
            hostname = address.getHostName();

        } catch (Exception e) {
            log.info("Cannot determine local hostname");
            hostname = "localhost";
        }
        hostInfo.setHostname(hostname);
        hostInfo.setIpAddress(address.getHostAddress());
        return hostInfo;
    }

    @ToString
    public static class HostInfo {
        private String ipAddress;
        private String hostname;

        public HostInfo(String hostname) {
            this.hostname = hostname;
        }

        public HostInfo() {
        }

        public int getIpAddressAsInt() {
            InetAddress inetAddress;
            String host = this.ipAddress;
            if (host == null) {
                host = this.hostname;
            }
            try {
                inetAddress = InetAddress.getByName(host);
            } catch (final UnknownHostException e) {
                throw new IllegalArgumentException(e);
            }
            return ByteBuffer.wrap(inetAddress.getAddress()).getInt();
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }
    }

}
