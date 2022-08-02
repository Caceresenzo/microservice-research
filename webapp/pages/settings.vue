<template>
    <v-main>
        <v-container>
            <v-row>
                <v-col cols="12">
                    <v-card>
                        <v-card-title>Appearance</v-card-title>
                        <v-card-text>
                            <v-switch v-model="theme" label="Theme" />
                        </v-card-text>
                    </v-card>
                </v-col>
                <v-col cols="12">
                    <v-card :loading="pending" title="API Keys">
                        <template #append>
                            <v-btn class="ml-auto" icon variant="flat" :loading="pending" @click="() => refresh()">
                                <v-icon>mdi-refresh</v-icon>
                            </v-btn>
                        </template>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </v-main>
</template>

<script lang="ts" setup>
import { useTheme } from "vuetify";

const vuetifyTheme = useTheme();
const theme = computed({
    get() {
        return vuetifyTheme.current.value.dark ? true : false
    },
    set(value) {
        vuetifyTheme.global.name.value = value ? "dark" : "light"
    }
})

const { $auth } = useNuxtApp()
const apiFetch = ($fetch as any).create({
    baseURL: 'http://localhost:8000/',
    async onRequest({ request, options }) {
        console.log('[fetch request]')
        console.log({ request, options })
    },
})

const { data, refresh, pending, error } = await useFetch(`/v1/api-keys`)
console.log(error)

</script>
