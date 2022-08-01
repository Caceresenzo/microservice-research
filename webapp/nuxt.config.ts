import { defineNuxtConfig } from 'nuxt'

// https://v3.nuxtjs.org/api/configuration/nuxt.config
export default defineNuxtConfig({
  // ssr: false,
  css: [
    'vuetify/lib/styles/main.sass',
    '@mdi/font/css/materialdesignicons.min.css'
  ],
  sourcemap: false,
  build: {
    transpile: ['vuetify'],
  },
  buildModules: [
    "@nuxtjs-alt/auth",
    "@nuxtjs-alt/axios",
    '@pinia/nuxt',
  ],
  vite: {
    define: {
      'process.env.DEBUG': false,
    },
    server: {
      watch: {
        usePolling: true
      }
    }
  },
  typescript: {
    shim: false,
  },
  auth: {
    pinia: {
      namespace: "auth"
    },
    rewriteRedirects: true,
    fullPathRedirect: false,
    watchLoggedIn: true,
    redirect: {
      login: '/auth/login',
      callback: '/auth/callback',
      logout: '/',
      home: '/',
    },
    localStorage: false,
    sessionStorage: false,
    cookie: {
      prefix: "token",
      options: {
        path: "/",
      },
    },
    defaultStrategy: "keycloak",
    strategies: {
      keycloak: {
        name: "keycloak",
        scheme: "openIDConnect",
        enabled: true,
        endpoints: {
          configuration: 'http://localhost:9800/realms/App/.well-known/openid-configuration',
          // authorization:
          //   "http://localhost:9800/" + "realms/App/protocol/openid-connect/auth",
          // token:
          //   "http://localhost:9800/" + "realms/App/protocol/openid-connect/token",
          // userInfo:
          //   "http://localhost:9800/" + "realms/App/protocol/openid-connect/userinfo",
          // logout:
          //   "http://localhost:9800/" + "realms/App/protocol/openid-connect/logout",
        },
        token: {
          property: "access_token",
          type: "Bearer",
          name: "Authorization",
          maxAge: 300,
        },
        refreshToken: {
          property: "refresh_token",
          maxAge: 60 * 60 * 24 * 30,
        },
        idToken: {
          property: "id_token"
        },
        responseType: "code",
        grantType: "authorization_code",
        clientId: "frontend",
        scope: ["openid", "profile", "email"],
        codeChallengeMethod: "S256",
        acrValues: "0",
      },
    }
  }
})
