<template>
    <v-layout id="main" style="width:100%;">      
        <div id="editorFrame" :style="{'height' : (height ? height+'px' : 'auto')}" :class="{focused : isFocus}">
            <ul :class="{focused : isFocus}">
                <li>
                    <button :class="{active : isActive}" @click="isActive = true">
                        Write
                    </button>
                </li>
                <li>
                    <button :class="{active : !isActive}" @click="isActive = false">
                        Preview
                    </button>
                </li>

                <li style="float:right;" v-show="isActive">
                    <v-tooltip bottom color="primary" open-on-hover open-on-focus open-on-click v-for="(item, i) in toolbar" :key="`${i}_toolbar`">
                        <template v-slot:activator="{ on, attrs }">
                            <v-icon 
                                v-bind="attrs"
                                v-on="on" 
                                class="toolbar" 
                                @click="clickToolbar(item.message)" 
                                :style="{'font-size' : item.size}"
                            >
                                {{item.icon}}
                            </v-icon>
                        </template>
                        <span>{{item.message}}</span>
                    </v-tooltip>

                </li>
            </ul>            
            <div id="editor" contenteditable v-show="isActive" @focusin="isFocus=true;" @focusout="isFocus=false;" @paste.prevent.self="checkPaste()" @input="onInput" @keypress="checkKeypress"></div>
            <div id="view" v-show="!isActive" v-html="parseContent"> </div>
        </div>
    </v-layout>
</template>

<script>
import axios from "axios";
export default {
    props: ['width', 'height', 'hideTab'], 
    watch: {
        isActive(){ 
            if(!this.isActive){
                this.parseContent = this.parseMd(this.content);
                console.dir(this.parseContent)
            }
        }
    },
    data() {
        return {
            isFocus: false,
            isActive: true,
            content: '',
            parseContent: '',

            toolbar: [
                {
                    icon: 'mdi-format-header-1',
                    message: 'header',
                    size: '18px',           
                },
                {
                    icon: 'mdi-format-bold',
                    message: 'bold',
                    size: '18px',
                },
                {
                    icon: 'mdi-format-italic',
                    message: 'italic',
                    size: '18px',
                },
                {
                    icon: 'mdi-format-underline',
                    message: 'underline',
                    size: '18px',
                },
                {
                    icon: 'mdi-format-strikethrough-variant',
                    message: 'strikethrough',
                    size: '15px',
                },
                {
                    icon: 'mdi-minus',
                    message: 'divider',
                    size: '18px',
                },
                {
                    icon: 'mdi-format-quote-close',
                    message: 'blockquote',
                    size: '18px',
                },
                {
                    icon: 'mdi-code-tags',
                    message: 'code',
                    size: '18px',
                },
                {
                    icon: 'mdi-paperclip mdi-rotate-45',
                    message: 'link',
                    size: '18px',
                },
                {
                    icon: 'mdi-fullscreen',
                    message: 'fullscreen',
                    size: '18px',
                },
            ]

        }
    },
    methods: {
        clickToolbar(msg){
            var selectionText = "";
            if (document.getSelection) {
                selectionText = document.getSelection().toString();
            } else if (document.selection) {
                selectionText = document.selection.createRange().text;
            }

            switch(msg){
                case 'header':
                    selectionText = `#${selectionText}`
                    break;
                case 'bold':
                    selectionText = `__${selectionText}__`
                    break;
                case 'italic':
                    selectionText = `_${selectionText}_`
                    break;
                case 'underline':
                    selectionText = `___${selectionText}___`
                    break;
                case 'strikethrough':
                    selectionText = `~~${selectionText}~~`
                    break;
                case 'divider':
                    selectionText = `---`
                    break;
                case 'blockquote':
                    selectionText = `>${selectionText}`
                    break;
                case 'code':
                    selectionText = `\`\`\`\n${selectionText}\n\`\`\``
                    break;
                case 'link':
                    selectionText = `[${selectionText}](url)`
                    break;
                case 'fullscreen':
                    break;
            }            
            document.execCommand('insertText', false, selectionText)
        },
        onInput(e){
            this.content = e.target.innerText;
        },
        checkKeypress(e) {
            // if(e.keyCode == 13){
            //     document.execCommand('insertHTML', false, '<br>');
            //     e.preventDefault();
            //     e.stopPropagation();

                
            // }
        },
        checkPaste(e){
            var items = (event.clipboardData || event.originalEvent.clipboardData).items;
            let flag = true;
            for (var index in items) {
                var item = items[index];
                if (item.kind === 'file') {
                    var blob = item.getAsFile();
                    this.imageTransfer(blob)
                    flag = false;
                }
            }
            if(flag) {
                let clipboardData = event.clipboardData || window.clipboardData;
                let pastedData = clipboardData.getData('Text');
                console.dir(pastedData)
                document.execCommand('insertText', false, pastedData)
            }
            return false;
        },

        imageTransfer(blob) {
            console.dir(this.$router.app.$store.state.token)
            let name = blob.name;

            var frm = new FormData();
            frm.append("file", blob);            
            axios.post('http://i3a101.p.ssafy.io:8080/api/v1/file/upload', frm, {
                headers: {
                    'accept': '*/*',
                    'X-AUTH-TOKEN': this.$router.app.$store.state.token,
                    'Content-Type': 'multipart/form-data'
                }
            }).then(({data}) => {
                console.dir(name)
                console.dir(data.result);
                document.execCommand('insertText', false, `![${name}](http://i3a101.p.ssafy.io/images/${data.result})`);
            })
            .catch((error) => {
                console.dir(error)
            }).finally(()=>{

            })
        },
        parseMd(md){
            //blockquote
            md = md.replace(/^\>(.+)/gm, '<blockquote>$1</blockquote>');
            
            //h
            md = md.replace(/[\#]{6}(.+)/g, '<h6>$1</h6>');
            md = md.replace(/[\#]{5}(.+)/g, '<h5>$1</h5>');
            md = md.replace(/[\#]{4}(.+)/g, '<h4>$1</h4>');
            md = md.replace(/[\#]{3}(.+)/g, '<h3>$1</h3>');
            md = md.replace(/[\#]{2}(.+)/g, '<h2>$1</h2>');
            md = md.replace(/[\#]{1}(.+)/g, '<h1>$1</h1>');
            
            //alt h
            md = md.replace(/^(.+)\n\=+/gm, '<h1>$1</h1>');
            md = md.replace(/^(.+)\n\-+/gm, '<h2>$1</h2>');
            
            //images
            md = md.replace(/\!\[([^\]]+)\]\(([^\)]+)\)/g, '<img src="$2" alt="$1" />');
            
            //links
            md = md.replace(/[\[]{1}([^\]]+)[\]]{1}[\(]{1}([^\)\"]+)(\"(.+)\")?[\)]{1}/g, '<a href="$2" title="$4">$1</a>');
            
            //font styles
            md = md.replace(/[\*\_]{3}([^\*\_]+)[\*\_]{3}/g, '<u>$1</u>');
            md = md.replace(/[\*\_]{2}([^\*\_]+)[\*\_]{2}/g, '<b>$1</b>');
            md = md.replace(/[\*\_]{1}([^\*\_]+)[\*\_]{1}/g, '<i>$1</i>');
            md = md.replace(/[\~]{2}([^\~]+)[\~]{2}/g, '<del>$1</del>');
            
            //pre
            // md = md.replace(/^\s*\n\`\`\`(([^\s]+))?/gm, '<pre class="$2">');
            // md = md.replace(/^\`\`\`\s*\n/gm, '</pre>\n\n');
            
            //code
            md = md.replace(/[\`]{3}([^\`]+)[\`]{3}/g, '<pre>$1</pre>');
            
            //p
            // md = md.replace(/^\s*(\n)?(.+)/gm, function(m){
            //     return  /\<(\/)?(h\d|ul|ol|li|blockquote|pre|img)/.test(m) ? m : '<p>'+m+'</p>';
            // });

            //hr
            md = md.replace(/[\-]{3}/g, '<hr />');

            //strip p from pre
            // md = md.replace(/(\<pre.+\>)\s*\n\<p\>(.+)\<\/p\>/gm, '$1$2');
            
            return md;            
        }
    }
}
</script>

<style scoped>
    

    .v-icon:hover{
        color:#1B69B6;
    }

    .v-icon.v-icon::after {
        border-radius: 50%;
        height: 0;
    }

    *{
        word-wrap: break-word;
        overflow-wrap: break-word;
        white-space: normal;
    }
    :focus {
        outline: none;
        /* #1B69B6 */
    }

    img {
        max-width: 100%; 
        height: auto;
    }
    #editor{
        line-height: 18px;
        margin-top:15px;
        font-size: 13px;
        width:100%;
        height:90%;
        overflow-y:auto;
    }

    #view{
        margin-top:15px;
        width:100%;
        height:90%;
        overflow-y:auto;
    }
    
    .v-application p{
        margin: 0px;
    }

    ul {
        list-style: none;
        padding-left: 0;
        border-bottom:1px solid #e5e5e5;
        width:100%; 
        height:33px;
    }

    .focused {        
        border-color:#80bdff;
    }

    li {
        display: inline; 
        height:32px;
    }
    
    .toolbar {
        font-size:18px;
    }

    button {
        height:32px;
        padding:0px 10px 11px 10px;
        font-size:14px;
        color:#707070;
    }

    button.active{
        border-bottom:2px solid #6666c4;
        color:#000000;
    }

    #editorFrame {
        width:98%;
        max-width:98%;
        color:#2e2e2e;
        background-color:#fff;
        border:1px solid #e5e5e5;
        outline: 0;
        padding:10px;
        margin:3px;
    }

    #editorFrame.focused {
        color:#2e2e2e;
        background-color:#fff;
        border-color:#80bdff;
        outline: 0;
        padding:10px;
        margin:3px;
        box-shadow: 0 0 0 0.2rem rgba(0,123,255,0.25);
    }
</style>