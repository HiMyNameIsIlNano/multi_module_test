export class Auth {

    private token: string;
    constructor(private email: string, public password: string) {}

    static withToken(email: string, token: string): Auth {
        const auth = new Auth(email, null);
        auth.token = token;
        return auth;
    }

    static fromLocalStorage(local: string): Auth {
        let username = null;
        let token = null;
        
        if (local != null){ 
            username = JSON.parse(local).username;
            token = JSON.parse(local).token;    
        }
        
        const auth = new Auth(username, null);
        auth.token = token;
        return auth;
    }

    getEmail(): string {
        return this.email;
    }

    getToken(): string {
        return this.token;
    }

}
