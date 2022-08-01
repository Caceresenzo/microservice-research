<template>
    <v-menu v-model="menu" location="bottom">
        <template #activator="{ props }">
            <v-list-item :prepend-avatar="avatar" :title="username" v-bind="props" />
        </template>
        <v-list>
            <v-list-item to="/account" prepend-icon="mdi-account">
                Manage account
            </v-list-item>
            <v-list-item to="/settings" prepend-icon="mdi-cog">
                Settings
            </v-list-item>
            <v-divider />
            <v-list-item to="/auth/logout" prepend-icon="mdi-logout">
                Log-out
            </v-list-item>
        </v-list>
    </v-menu>
</template>

<script lang="ts" setup>
const { $auth } = useNuxtApp()
const { user } = $auth

const menu = ref(false)
const avatar = "https://randomuser.me/api/portraits/men/85.jpg"
const username = computed(() => user.name)

</script>