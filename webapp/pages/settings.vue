<template>
    <v-main>
        <v-container>
            <v-row>
                <v-col>
                    <v-card>
                        <v-card-title>Appearance</v-card-title>
                        <v-card-text>
                            <v-switch v-model="theme" label="Theme" />
                        </v-card-text>
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
</script>
