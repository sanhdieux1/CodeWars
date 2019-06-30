const PROXY_CONFIG = [
    {
      context: [
        // "/free-member-service/domain-whitelist.json",
          "/free-member-service"
      ],
      target: "http://wlqat.beatus88.net:8080",
      secure: false,
      "changeOrigin": true,
      "logLevel": "debug",
      headers: {host: 'wlqat.beatus88.net'},
      cookieDomainRewrite: {
        "wlqat.beatus88.net": "wlqat.beatus88.net"
      }
    }
    ,
    {
      context: [
          // "/free-member-service",
          "/exchange-service"
      ],
      target: "http://wlqat.beatus88.com",
      secure: false,
      "changeOrigin": true,
      "logLevel": "debug",
      headers: {host: 'wlqat.beatus88.com'},
      cookieDomainRewrite: {
        "wlqat.beatus88.com": "wlqat.beatus88.net"
      }
    }
  ];

  module.exports = PROXY_CONFIG;
