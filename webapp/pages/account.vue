<template>
    <v-main>
        <v-container>
            <v-row>
                <v-col>
                    <v-card>
                        <pre>{{ user }}</pre>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </v-main>
</template>

<script lang="ts" setup>
const { $auth } = useNuxtApp();
const user = $auth.user;
</script>
