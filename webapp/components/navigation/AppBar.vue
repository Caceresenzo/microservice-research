<template>
    <v-app-bar>
        <v-app-bar-title>
            Hello
        </v-app-bar-title>
        <v-spacer />
        <AccountMenu v-if="loggedIn" />
        <AuthButtonLogin v-else />
    </v-app-bar>
</template>

<script lang="ts" setup>
const { $auth } = useNuxtApp()
const { loggedIn } = $auth
</script>
