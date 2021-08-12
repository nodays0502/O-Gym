const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "/api",
    createProxyMiddleware({
      target: "https://i5b305.p.ssafy.io",
      changeOrigin: true,
    })
  );
};
