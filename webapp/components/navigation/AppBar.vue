<template>
    <v-app-bar>
        <v-app-bar-title>
            Hello
        </v-app-bar-title>
        <v-spacer />
        <AccountMenu v-if="loggedIn" />
        <template v-else>
            <AuthButtonRegister class="mr-2" />
            <AuthButtonLogin />
        </template>
    </v-app-bar>
</template>

<script lang="ts" setup>
const { $auth } = useNuxtApp()
const { loggedIn } = $auth
</script>
