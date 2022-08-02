<template>
    <v-main>
        <v-row class="background">
            <v-col class="pr-0 d-flex align-center justify-center" style="height: 100vh">
                <v-card elevation="0" width="500" :loading="loading">
                    <v-card-title class="text-h2 text-center" style="line-height: unset;">
                        WELCOME
                    </v-card-title>
                    <v-card-subtitle class="text-center">
                        Registration
                    </v-card-subtitle>
                    <v-card-text>
                        <v-form>
                            <v-text-field v-model="firstName" :disabled="loading" label="First Name" variant="outlined" />
                            <v-text-field v-model="lastName" :disabled="loading" label="Last Name" variant="outlined" />
                            <v-text-field v-model="email" :disabled="loading" label="Email" variant="outlined" type="email" />
                            <v-text-field v-model="password" :disabled="loading" label="Password" variant="outlined" type="password" />
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <div class="d-flex flex-column align-center justify-center v-btn--block">
                            <v-btn :disabled="loading" size="x-large" variant="tonal" block @click="submit">
                                Register
                            </v-btn>
                            <div v-if="!loading" class="mt-2">
                                Already have an account? <RouterLink to="/auth/login">Log-in</RouterLink>
                            </div>
                        </div>
                    </v-card-actions>
                </v-card>
            </v-col>
            <v-col class="pl-0" style="height: 100vh" />
        </v-row>
    </v-main>
</template>

<script lang="ts" setup>
definePageMeta({
    layout: "empty",
});

const loading = ref(false)
const error = ref(null)

const firstName = ref(null)
const lastName = ref(null)
const email = ref(null)
const password = ref(null)

async function submit() {
    try {
        error.value = null
        loading.value = true

        const response = $fetch('/api/v1/auth/register', {
            method: 'POST',
            body: toRaw({
                firstName,
                lastName,
                email,
                password
            })
        })
    } catch (error_) {
        error.value = error_
    } finally {
        loading.value = false
    }
}
</script>

<style scoped>
.background {
    background-image: url(https://wallpaper.dog/large/5438626.jpg);
}
</style>